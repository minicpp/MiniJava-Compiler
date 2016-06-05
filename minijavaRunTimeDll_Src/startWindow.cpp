#include <windows.h>
#include "startWindow.h"
#include <stdio.h>
#define STYLE WS_OVERLAPPED|WS_CAPTION|WS_SYSMENU|WS_MINIMIZEBOX

MYBITMAPINFO g_Info;

typedef int ( __stdcall *MessageHandler)(int *paramArray,int num);

LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam);
void CreateDib(int width,int height);
void CALLBACK BackgroundProcessing();


char *className="MiniJavaWindow";
unsigned int *g_MiniDraw;
unsigned int g_tick;
int *g_paramArray;
int g_pArray[17];
int g_num=16;
int g_Width;
int g_Height;

MessageHandler g_hand;
HWND hWnd;//Handle of the main window

void StartWindow(int width,int height,char *ptitle,unsigned int *pDraw)
{
	g_Width=width;
	g_Height=height;
	CreateDib(width,height);
	if(hWnd)
	{
		RECT rect={0,0,width,height};
		AdjustWindowRect(&rect,STYLE,FALSE);
		SetWindowText(hWnd,ptitle);
		MoveWindow(hWnd,89,89,rect.right-rect.left,rect.bottom-rect.top,TRUE);
		return;
	}
	MSG msg;
	g_MiniDraw=pDraw;
	g_pArray[0]=g_num;
	g_paramArray=&(g_pArray[1]);
	HINSTANCE hInstance=GetModuleHandle(NULL);
		
	WNDCLASSEX wcex;
	wcex.cbSize			= sizeof(WNDCLASSEX);
	wcex.style			= CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc	= (WNDPROC)WndProc;
	wcex.cbClsExtra		= 0;
	wcex.cbWndExtra		= 0;
	wcex.hInstance		= hInstance;
	wcex.hIcon			= LoadIcon(hInstance, IDI_APPLICATION);
	wcex.hCursor		= LoadCursor(NULL, IDC_ARROW);
	wcex.hbrBackground	= (HBRUSH)(COLOR_WINDOW+1);
	wcex.lpszMenuName	= NULL;
	wcex.lpszClassName	= className;
	wcex.hIconSm		= LoadIcon(wcex.hInstance, IDI_APPLICATION);

	RegisterClassEx(&wcex);

	RECT rect={0,0,width,height};
//	printf("before:%d %d %d %d\n",rect.left,rect.top,rect.right,rect.bottom);
	AdjustWindowRectEx(&rect,STYLE ,FALSE,NULL);
//	printf("after:%d %d %d %d\n",rect.left,rect.top,rect.right,rect.bottom);
	
	hWnd = CreateWindow(className,ptitle,STYLE ,
       89, 89,rect.right-rect.left,rect.bottom-rect.top,NULL,NULL,hInstance, NULL);

   if (!hWnd)
   {
      return;
   }

   ShowWindow(hWnd, SW_SHOW);
   UpdateWindow(hWnd);
 //  GetClientRect(hWnd,&rect);
  // printf("size:%d %d %d %d\n",rect.left,rect.top,rect.right,rect.bottom);

   //The first item in MiniDraw virtual function is assigned to g_hand
   __asm
	{
		mov eax,pDraw;
		mov eax,[eax]
		mov eax,[eax]
		mov ebx,offset g_hand
		mov [ebx],eax
	}

	// Main message loop:
    g_tick=GetTickCount();
	while(true)
    {   
		while (PeekMessage(&msg, NULL, 0, 0, PM_REMOVE))
		{
			if (msg.message == WM_QUIT)
				return;
			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
		BackgroundProcessing();
    }
}


LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	PAINTSTRUCT ps;
	HDC hdc;

	switch (message) 
	{
		case WM_PAINT:
			hdc = BeginPaint(hWnd, &ps);
			// TODO: Add any drawing code here...
			EndPaint(hWnd, &ps);
			break;
		case WM_DESTROY:
			delete[] g_Info.buffer;
			g_Info.buffer=0;
			PostQuitMessage(0);
			break;
		default:
			return DefWindowProc(hWnd, message, wParam, lParam);
   }
   return 0;
}
int i=0;
void CALLBACK BackgroundProcessing()
{
	int g_old;
	g_old=g_tick;
	g_tick=GetTickCount();
	g_paramArray[0]=g_tick-g_old;
	__asm
	{
		//recover ecx as "this" pointer of MiniDraw
		mov ecx,g_MiniDraw
		push g_num
		push g_paramArray
		call g_hand
		
	}
}

void CreateDib(int width,int height)
{
	if(g_Info.buffer)
		delete[] g_Info.buffer;
	memset(&g_Info,0,sizeof(MYBITMAPINFO));
	g_Info.bmiHeader.biSize=sizeof(BITMAPINFOHEADER);
	g_Info.bmiHeader.biWidth=width;
	g_Info.bmiHeader.biHeight=(-1)*height;
	g_Info.bmiHeader.biBitCount=32;
	g_Info.bmiHeader.biClrUsed=0;
	g_Info.bmiHeader.biPlanes=1;
	g_Info.buffer=new int[width*height];
}

void BitBltToDevice(int *pSource,int sw,int sh,int dx,int dy)
{
	RECT src,dest,urect;
	//left top is pixel at the left most and top most.
	//right bottom 为右下索引位置

	//mapping to original position to the screen position.
	src.left=dx;
	src.top=dy;
	src.right=src.left+sw;
	src.bottom=src.top+sh;
	//the screen position is not changed
	dest.left=0;
	dest.top=0;
	dest.right=g_Width;
	dest.bottom=g_Height;

	

	//get clipped region left
	if(src.left>dest.right-1)
	{
		return;
	}
	else if(src.left < dest.left )
	{
		urect.left=dest.left;
	}
	else
	{
		urect.left=src.left;
	}


	//get clipped region right
	if(src.right-1<dest.left)
	{
		return;
	}
	else if(src.right>dest.right)
	{
		urect.right=dest.right;
	}
	else
	{
		urect.right=src.right;
	}
	
	//get clipped region top
	if(src.top > dest.bottom-1 )
	{
		return;
	}
	else if(src.top < dest.top)
	{
		urect.top=dest.top;
	}
	else
		urect.top=src.top;

	//get clipped region bottom
	if(src.bottom-1<dest.top)
	{
		return;
	}
	else if(src.bottom > dest.bottom)
		urect.bottom=dest.bottom;
	else
		urect.bottom=src.bottom;



	int *pdestbuf=g_Info.buffer;
	pdestbuf+=urect.top*g_Width+urect.left; // get position of destination buffer row*width + col

	//convert screen position to original position
	urect.left-=dx;	
	urect.right-=dx;
	urect.top-=dy;
	urect.bottom-=dy;	
	
	

	//source array, to get position
	int *psourcebuf=pSource;
	psourcebuf+=urect.top*sw+urect.left;//get row*width+col
	

	//copy the height
	int times=urect.bottom-urect.top;
	//width for each copy
	int length=urect.right-urect.left;
	//bytes for a copy
	int size=sizeof(int)*length;
	//original array, width of each row
	int sourcePitch=sw;
	//desstination, width of each row
	int destPitch=g_Width;

	for(int i=0;i<times;++i)
	{
	//	printf("i=%d,times=%d\n",i,times);
		memcpy(pdestbuf,psourcebuf,size);
		pdestbuf+=destPitch;
		psourcebuf+=sourcePitch;		
	}

}
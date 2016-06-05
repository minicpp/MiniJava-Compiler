#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include "startWindow.h"

#define EX extern "C" __declspec(dllexport)
#define PUSH_REG __asm push ecx 
#define POP_REG  __asm pop  ecx
EX void __stdcall minij_runtime_system_out_println(int i)
{
	PUSH_REG
	printf("%d\n",i);
	POP_REG
}
EX void __stdcall minij_runtime_minijava_print(int i)
{
	PUSH_REG
	printf("%c",i);
	POP_REG
}
EX void __stdcall minij_runtime_minijava_scanf(int *pi)
{
	PUSH_REG
	scanf("%d",pi);
	POP_REG
}
EX void __stdcall minij_runtime_exit()
{
	exit(0);
}
EX char* __stdcall minij_runtime_alloc(int i)
{
	PUSH_REG
	if(i<0)
	{
		__asm int 3
	}
	char* pmem=(char *)malloc(sizeof(char)*i);
	memset(pmem,0,sizeof(char)*i);
	POP_REG
	return pmem;
}

EX void _stdcall minij_runtime_minijava_window(int w,int h,int *t,unsigned int *pDraw)
{	
	PUSH_REG	
	t=t-1;
	int length=*t;
	t=t+1;
//	printf("%d",length);
	char *str=new char[length+1];
	int i;
	for(i=0;i<length;++i)
	{
		str[i]=t[i];
	}
	str[i]=0;
	StartWindow(w,h,str,pDraw);
	delete []str;
	POP_REG
}

EX void _stdcall minij_runtime_minijava_wait(int i)
{
	PUSH_REG
	Sleep(i);
	POP_REG
}

//if the 0 is settled, it will test if k is pressed down
//if non-zero is settled, it will test if k is pressed up
EX void _stdcall minij_runtime_minijava_getkeystate(int k,int *r)
{
	PUSH_REG
	if( *r == 0)
	{
		*r=((GetAsyncKeyState(k)&0x8000)?1:0);
	}
	else
	{
		*r=((GetAsyncKeyState(k)&0x8000)?0:1);
	}
	
	POP_REG
}

EX void _stdcall minij_runtime_minijava_bitblt(int *b,int sw,int sh,int dx,int dy)
{
	PUSH_REG
	BitBltToDevice(b,sw,sh,dx,dy);
	POP_REG
}

EX void _stdcall minij_runtime_minijava_color(unsigned int r,unsigned int g,
											  unsigned int b,unsigned int a,unsigned int *c)
{
	PUSH_REG
	*c= ((((a)&0xff)<<24)|(((r)&0xff)<<16)|(((g)&0xff)<<8)|((b)&0xff));
	POP_REG
}

EX void _stdcall minij_runtime_minijava_present(int i,int c)
{
	PUSH_REG
	HDC dc=GetDC(hWnd);
	SetDIBitsToDevice(dc,0,0,g_Width,g_Height,0,0,0,
		g_Height,g_Info.buffer,
		(struct tagBITMAPINFO *)(&g_Info),DIB_RGB_COLORS);
	ReleaseDC(hWnd,dc);	
	if(i)
	{
		int length=g_Width*g_Height;
		for(int i=0;i<length;++i)
		{
			g_Info.buffer[i]=c;
		}
	}
	POP_REG
}

EX void _stdcall minij_runtime_minijava_srand(int i)
{
	PUSH_REG
	
	if(i==0)
	{			
		srand(GetTickCount());
	}
	else
	{		
		srand(i);
	}
	POP_REG
}

EX void _stdcall minij_runtime_minijava_rand(int *i)
{
	PUSH_REG
	*i=rand();
	POP_REG
}
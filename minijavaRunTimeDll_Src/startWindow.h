void StartWindow(int width,int height,char *ptitle,unsigned int *pDraw);
void BitBltToDevice(int *pSource,int sw,int sh,int dx,int dy);
extern int g_Width;
extern int g_Height;
extern HWND hWnd;
struct MYBITMAPINFO {
    BITMAPINFOHEADER    bmiHeader;
    RGBQUAD             bmiColors[256];
	int*				buffer;
};
extern MYBITMAPINFO g_Info;
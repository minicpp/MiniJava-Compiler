;This asm generate from MiniJava Parser.Present by HanDong:)
.386
.model flat,stdcall
option casemap:none
include minijava.inc
includelib minijava.lib
.const
@version@ db 'MiniJava 0.0.1 Alpha Powered by DDRMSDOS (minicpp)',0
table@Score  \
dd  method@Score@PrintTotal
dd  method@Score@GameOver
dd  method@Score@AddScore
table@Game  \
dd  method@Game@MessageHandler
dd  method@Game@Delay
table@Credit  \
dd  method@Credit@getEnable
dd  method@Credit@Draw
dd  method@Credit@setType
dd  method@Credit@setEnable
dd  method@Credit@Init
dd  method@Credit@getType
dd  method@Credit@setY
dd  method@Credit@setX
dd  method@Credit@getY
dd  method@Credit@getX
table@GameMain  \
dd  method@GameMain@Init
dd  method@GameMain@Draw
dd  method@GameMain@Update
table@SnakeBody  \
dd  method@SnakeBody@getEnd
dd  method@SnakeBody@getY
dd  method@SnakeBody@getX
dd  method@SnakeBody@getNext
dd  method@SnakeBody@setY
dd  method@SnakeBody@setNext
dd  method@SnakeBody@setEnd
dd  method@SnakeBody@setX
table@SnakeList  \
dd  method@SnakeList@ResetCredit
dd  method@SnakeList@Draw
dd  method@SnakeList@AdjustNode
dd  method@SnakeList@getFall
dd  method@SnakeList@Init
dd  method@SnakeList@Go
dd  method@SnakeList@UpdateList
dd  method@SnakeList@view
dd  method@SnakeList@AddNode
table@SnakeTile  \
dd  method@Tile@Create
dd  method@SnakeTile@Init
dd  method@Tile@Draw
dd  method@SnakeTile@GetSnake
dd  method@SnakeTile@GetXPos
dd  method@SnakeTile@DrawItem
dd  method@SnakeTile@Update
dd  method@SnakeTile@SnakeDraw
dd  method@SnakeTile@InitSnake
dd  method@SnakeTile@GetYPos
dd  method@SnakeTile@SetSnake
table@WallTile  \
dd  method@WallTile@Create
dd  method@WallTile@Init
dd  method@WallTile@Draw
dd  method@WallTile@DrawWall
table@Window  \
dd  method@Window@Run
table@MiniDraw  \
dd  method@MiniDraw@MessageHandler
table@Tile  \
dd  method@Tile@Create
dd  method@Tile@Init
dd  method@Tile@Draw
.code
main@Snake:
push ebx
push 4
call minij_runtime_alloc
mov ebx,offset table@Window
mov [eax],ebx
pop ebx
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
push eax
call minij_runtime_system_out_println
call minij_runtime_exit
method@Score@PrintTotal:
push ebp
mov ebp,esp
add esp,0
mov eax,83
push eax
call minij_runtime_minijava_print
mov eax,99
push eax
call minij_runtime_minijava_print
mov eax,111
push eax
call minij_runtime_minijava_print
mov eax,114
push eax
call minij_runtime_minijava_print
mov eax,101
push eax
call minij_runtime_minijava_print
mov eax,58
push eax
call minij_runtime_minijava_print
mov eax,[ecx+4]
push eax
call minij_runtime_system_out_println
mov eax,0
leave
retn 0
method@Score@GameOver:
push ebp
mov ebp,esp
add esp,0
mov eax,71
push eax
call minij_runtime_minijava_print
mov eax,97
push eax
call minij_runtime_minijava_print
mov eax,109
push eax
call minij_runtime_minijava_print
mov eax,101
push eax
call minij_runtime_minijava_print
mov eax,32
push eax
call minij_runtime_minijava_print
mov eax,111
push eax
call minij_runtime_minijava_print
mov eax,118
push eax
call minij_runtime_minijava_print
mov eax,101
push eax
call minij_runtime_minijava_print
mov eax,114
push eax
call minij_runtime_minijava_print
mov eax,46
push eax
call minij_runtime_minijava_print
mov eax,10
push eax
call minij_runtime_minijava_print
mov eax,70
push eax
call minij_runtime_minijava_print
mov eax,105
push eax
call minij_runtime_minijava_print
mov eax,110
push eax
call minij_runtime_minijava_print
mov eax,97
push eax
call minij_runtime_minijava_print
mov eax,108
push eax
call minij_runtime_minijava_print
mov eax,32
push eax
call minij_runtime_minijava_print
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@Score@AddScore:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov [ecx+4],eax
mov eax,0
leave
retn 4
method@Tile@Create:
push ebp
mov ebp,esp
add esp,0
mov eax,0
leave
retn 8
method@Tile@Init:
push ebp
mov ebp,esp
add esp,0
mov eax,0
leave
retn 0
method@Tile@Draw:
push ebp
mov ebp,esp
add esp,0
mov eax,0
leave
retn 8
method@WallTile@Create:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
push ebx
push edx
mov ebx,eax
mov eax,[ebp+12]
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+4],eax
mov eax,[ebp+8]
push ebx
push edx
mov ebx,eax
mov eax,[ebp+12]
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+8],eax
mov eax,0
leave
retn 8
method@WallTile@Init:
push ebp
mov ebp,esp
add esp,-24
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,25
push eax
mov eax,25
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+4]
mov eax,[eax-4]
mov [ebp-20],eax
lea eax,[ebp-12]
push eax
mov eax,255
push eax
mov eax,41
push eax
mov eax,161
push eax
mov eax,59
push eax
call minij_runtime_minijava_color
lea eax,[ebp-16]
push eax
mov eax,255
push eax
mov eax,13
push eax
mov eax,110
push eax
mov eax,173
push eax
call minij_runtime_minijava_color
mov eax,0
mov [ebp-4],eax
Label@1:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,[ebp-20]
cmp ebx,eax
jge Label@2
mov eax,1
jmp Label@3
Label@2:
xor eax,eax
Label@3:
pop ebx
test eax,eax
jz Label@0
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jle Label@4
mov eax,1
jmp Label@5
Label@4:
xor eax,eax
Label@5:
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,25
cmp ebx,eax
jge Label@6
mov eax,1
jmp Label@7
Label@6:
xor eax,eax
Label@7:
pop ebx
and eax,ebx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,600
cmp ebx,eax
jle Label@8
mov eax,1
jmp Label@9
Label@8:
xor eax,eax
Label@9:
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,625
cmp ebx,eax
jge Label@10
mov eax,1
jmp Label@11
Label@10:
xor eax,eax
Label@11:
pop ebx
and eax,ebx
pop ebx
or eax,ebx
pop ebx
push ebx
mov ebx,eax
mov eax,25
push ebx
mov ebx,eax
mov eax,[ebp-4]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@12
mov eax,1
jmp Label@13
Label@12:
xor eax,eax
Label@13:
pop ebx
push ebx
mov ebx,eax
mov eax,25
push ebx
mov ebx,eax
mov eax,[ebp-4]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,24
cmp ebx,eax
jne Label@14
mov eax,1
jmp Label@15
Label@14:
xor eax,eax
Label@15:
pop ebx
or eax,ebx
pop ebx
or eax,ebx
pop ebx
test eax,eax
jz Label@16
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ebp-16]
mov [ebx],eax
pop ebx
jmp Label@17
Label@16:
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ebp-12]
mov [ebx],eax
pop ebx
Label@17:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@1
Label@0:
mov eax,0
mov [ebp-4],eax
mov eax,5
push ebx
push edx
mov ebx,eax
mov eax,25
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,5
add eax,ebx
pop ebx
mov [ebp-24],eax
Label@19:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,15
cmp ebx,eax
jge Label@20
mov eax,1
jmp Label@21
Label@20:
xor eax,eax
Label@21:
pop ebx
test eax,eax
jz Label@18
mov eax,0
mov [ebp-8],eax
Label@23:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,15
cmp ebx,eax
jge Label@24
mov eax,1
jmp Label@25
Label@24:
xor eax,eax
Label@25:
pop ebx
test eax,eax
jz Label@22
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-24]
push ebx
mov ebx,eax
mov eax,[ebp-8]
add eax,ebx
pop ebx
lea ebx,[ebx][eax*4]
mov eax,[ebp-16]
mov [ebx],eax
pop ebx
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-8],eax
jmp Label@23
Label@22:
mov eax,[ebp-24]
push ebx
mov ebx,eax
mov eax,25
add eax,ebx
pop ebx
mov [ebp-24],eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@19
Label@18:
mov eax,0
mov [ebp-4],eax
Label@27:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,[ebp-20]
cmp ebx,eax
jge Label@28
mov eax,1
jmp Label@29
Label@28:
xor eax,eax
Label@29:
pop ebx
test eax,eax
jz Label@26
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-4]
mov eax,[ebx][eax*4]
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-16]
cmp ebx,eax
jne Label@30
mov eax,1
jmp Label@31
Label@30:
xor eax,eax
Label@31:
pop ebx
test eax,eax
jz Label@32
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ebp-12]
mov [ebx],eax
pop ebx
jmp Label@33
Label@32:
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ebp-16]
mov [ebx],eax
pop ebx
Label@33:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@27
Label@26:
mov eax,[ecx+4]
mov [ecx+12],eax
mov eax,0
leave
retn 0
method@WallTile@Draw:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push eax
mov eax,25
push eax
mov eax,25
push eax
mov eax,[ecx+12]
push eax
call minij_runtime_minijava_bitblt
mov eax,0
leave
retn 8
method@WallTile@DrawWall:
push ebp
mov ebp,esp
add esp,-8
mov eax,0
mov [ebp-4],eax
mov eax,25
mov [ebp-8],eax
Label@35:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,500
cmp ebx,eax
jge Label@36
mov eax,1
jmp Label@37
Label@36:
xor eax,eax
Label@37:
pop ebx
test eax,eax
jz Label@34
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,0
push eax
mov eax,[ebp-4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,475
push eax
mov eax,[ebp-4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,25
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@35
Label@34:
Label@39:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,500
cmp ebx,eax
jge Label@40
mov eax,1
jmp Label@41
Label@40:
xor eax,eax
Label@41:
pop ebx
test eax,eax
jz Label@38
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push eax
mov eax,0
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push eax
mov eax,475
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,25
add eax,ebx
pop ebx
mov [ebp-8],eax
jmp Label@39
Label@38:
mov eax,[ecx+16]
push ebx
mov ebx,eax
mov eax,200
cmp ebx,eax
jge Label@42
mov eax,1
jmp Label@43
Label@42:
xor eax,eax
Label@43:
pop ebx
test eax,eax
jz Label@44
mov eax,[ecx+16]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ecx+16],eax
jmp Label@45
Label@44:
mov eax,0
mov [ecx+16],eax
mov eax,[ecx+12]
push ebx
mov ebx,eax
mov eax,[ecx+4]
cmp ebx,eax
jne Label@46
mov eax,1
jmp Label@47
Label@46:
xor eax,eax
Label@47:
pop ebx
test eax,eax
jz Label@48
mov eax,[ecx+8]
mov [ecx+12],eax
jmp Label@49
Label@48:
mov eax,[ecx+4]
mov [ecx+12],eax
Label@49:
Label@45:
mov eax,0
leave
retn 0
method@SnakeBody@getX:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+4]
leave
retn 0
method@SnakeBody@getY:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+8]
leave
retn 0
method@SnakeBody@setX:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+4],eax
mov eax,0
leave
retn 4
method@SnakeBody@setY:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+8],eax
mov eax,0
leave
retn 4
method@SnakeBody@setEnd:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+16],eax
mov eax,0
leave
retn 4
method@SnakeBody@getEnd:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+16]
leave
retn 0
method@SnakeBody@setNext:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+12],eax
mov eax,0
leave
retn 4
method@SnakeBody@getNext:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+12]
leave
retn 0
method@Credit@setEnable:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+40],eax
mov eax,1
leave
retn 4
method@Credit@getEnable:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+40]
leave
retn 0
method@Credit@setType:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+24],eax
mov eax,0
leave
retn 4
method@Credit@getType:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+24]
leave
retn 0
method@Credit@getX:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+28]
leave
retn 0
method@Credit@getY:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+32]
leave
retn 0
method@Credit@setX:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+28],eax
mov eax,0
leave
retn 4
method@Credit@setY:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
mov [ecx+32],eax
mov eax,0
leave
retn 4
method@Credit@Init:
push ebp
mov ebp,esp
add esp,-8
mov eax,25
push ebx
push edx
mov ebx,eax
mov eax,25
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+4],eax
mov eax,25
push ebx
push edx
mov ebx,eax
mov eax,25
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+8],eax
lea eax,[ecx+16]
push eax
mov eax,255
push eax
mov eax,82
push eax
mov eax,241
push eax
mov eax,186
push eax
call minij_runtime_minijava_color
lea eax,[ecx+20]
push eax
mov eax,255
push eax
mov eax,13
push eax
mov eax,110
push eax
mov eax,173
push eax
call minij_runtime_minijava_color
mov eax,[ecx+4]
mov eax,[eax-4]
mov [ebp-8],eax
mov eax,0
mov [ebp-4],eax
Label@51:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,[ebp-8]
cmp ebx,eax
jge Label@52
mov eax,1
jmp Label@53
Label@52:
xor eax,eax
Label@53:
pop ebx
test eax,eax
jz Label@50
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ecx+16]
mov [ebx],eax
pop ebx
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ecx+20]
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@51
Label@50:
mov eax,[ecx+4]
mov [ecx+12],eax
mov eax,0
leave
retn 0
method@Credit@Draw:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+40]
test eax,eax
jz Label@54
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
jne Label@55
mov eax,1
jmp Label@56
Label@55:
xor eax,eax
Label@56:
pop ebx
test eax,eax
jz Label@57
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push eax
mov eax,25
push eax
mov eax,25
push eax
mov eax,[ecx+4]
push eax
call minij_runtime_minijava_bitblt
jmp Label@58
Label@57:
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push eax
mov eax,25
push eax
mov eax,25
push eax
mov eax,[ecx+12]
push eax
call minij_runtime_minijava_bitblt
mov eax,[ecx+36]
push ebx
mov ebx,eax
mov eax,10
cmp ebx,eax
jle Label@59
mov eax,1
jmp Label@60
Label@59:
xor eax,eax
Label@60:
pop ebx
test eax,eax
jz Label@61
mov eax,0
mov [ecx+36],eax
mov eax,[ecx+12]
push ebx
mov ebx,eax
mov eax,[ecx+4]
cmp ebx,eax
jne Label@62
mov eax,1
jmp Label@63
Label@62:
xor eax,eax
Label@63:
pop ebx
test eax,eax
jz Label@64
mov eax,[ecx+8]
mov [ecx+12],eax
jmp Label@65
Label@64:
mov eax,[ecx+4]
mov [ecx+12],eax
Label@65:
jmp Label@66
Label@61:
mov eax,[ecx+36]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ecx+36],eax
Label@66:
Label@58:
Label@54:
mov eax,0
leave
retn 8
method@SnakeList@getFall:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+40]
leave
retn 0
method@SnakeList@Init:
push ebp
mov ebp,esp
add esp,0
push ebx
push 8
call minij_runtime_alloc
mov ebx,offset table@Score
mov [eax],ebx
pop ebx
mov [ecx+20],eax
xor eax,eax
mov [ecx+40],eax
push ebx
push 44
call minij_runtime_alloc
mov ebx,offset table@Credit
mov [eax],ebx
pop ebx
mov [ecx+16],eax
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
push ebx
push 20
call minij_runtime_alloc
mov ebx,offset table@SnakeBody
mov [eax],ebx
pop ebx
mov [ecx+12],eax
mov eax,[ebp+20]
mov [ecx+36],eax
push ebx
push 20
call minij_runtime_alloc
mov ebx,offset table@SnakeBody
mov [eax],ebx
pop ebx
mov [ecx+4],eax
mov eax,[ecx+28]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ecx+28],eax
mov eax,[ecx+4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+4]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,24
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+16]
mov [ecx+24],eax
mov eax,[ebp+16]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@67
mov eax,1
jmp Label@68
Label@67:
xor eax,eax
Label@68:
pop ebx
test eax,eax
jz Label@69
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,2
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,2
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@70
Label@69:
mov eax,[ebp+16]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
jne Label@71
mov eax,1
jmp Label@72
Label@71:
xor eax,eax
Label@72:
pop ebx
test eax,eax
jz Label@73
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,2
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,2
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@74
Label@73:
mov eax,[ebp+16]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
jne Label@75
mov eax,1
jmp Label@76
Label@75:
xor eax,eax
Label@76:
pop ebx
test eax,eax
jz Label@77
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,2
add eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,2
add eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@78
Label@77:
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,2
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp+20]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+12]
push ebx
mov ebx,eax
mov eax,2
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
Label@78:
Label@74:
Label@70:
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 16
method@SnakeList@ResetCredit:
push ebp
mov ebp,esp
add esp,-16
mov eax,1
mov [ebp-4],eax
Label@80:
mov eax,[ebp-4]
test eax,eax
jz Label@79
lea eax,[ebp-8]
push eax
call minij_runtime_minijava_rand
lea eax,[ebp-12]
push eax
call minij_runtime_minijava_rand
mov eax,18
push ebx
mov ebx,eax
mov eax,[ebp-8]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
mov [ebp-8],eax
mov eax,18
push ebx
mov ebx,eax
mov eax,[ebp-12]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
mov [ebp-12],eax
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-12]
push eax
mov eax,[ebp-8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@81
mov eax,1
jmp Label@82
Label@81:
xor eax,eax
Label@82:
pop ebx
test eax,eax
jz Label@83
xor eax,eax
mov [ebp-4],eax
Label@83:
jmp Label@80
Label@79:
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-12]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,24
call dword ptr [eax]
pop ecx
pop ebx
lea eax,[ebp-16]
push eax
call minij_runtime_minijava_rand
mov eax,2
push ebx
mov ebx,eax
mov eax,[ebp-16]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@84
mov eax,1
jmp Label@85
Label@84:
xor eax,eax
Label@85:
pop ebx
test eax,eax
jz Label@86
mov eax,2
mov [ebp-16],eax
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,2
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@87
Label@86:
mov eax,3
mov [ebp-16],eax
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,3
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
Label@87:
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-16]
push eax
mov eax,[ebp-12]
push eax
mov eax,[ebp-8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-16]
leave
retn 0
method@SnakeList@AddNode:
push ebp
mov ebp,esp
add esp,-4
mov eax,[ecx+28]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ecx+28],eax
push ebx
push 20
call minij_runtime_alloc
mov ebx,offset table@SnakeBody
mov [eax],ebx
pop ebx
mov [ebp-4],eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
xor eax,eax
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,24
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+12]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov eax,[ecx+4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
mov [ecx+4],eax
mov eax,0
leave
retn 8
method@SnakeList@view:
push ebp
mov ebp,esp
add esp,-8
mov eax,[ecx+4]
mov [ebp-8],eax
xor eax,eax
mov [ebp-4],eax
Label@89:
mov eax,[ebp-4]
xor eax,1
test eax,eax
jz Label@88
mov eax,[ebp-8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-4],eax
mov eax,[ebp-8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-8],eax
jmp Label@89
Label@88:
mov eax,0
leave
retn 0
method@SnakeList@Draw:
push ebp
mov ebp,esp
add esp,-20
mov eax,[ecx+28]
mov [ebp-8],eax
mov eax,[ecx+4]
mov [ebp-20],eax
xor eax,eax
mov [ebp-4],eax
mov eax,2
push ebx
mov ebx,eax
mov eax,[ebp-8]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@90
mov eax,1
jmp Label@91
Label@90:
xor eax,eax
Label@91:
pop ebx
test eax,eax
jz Label@92
mov eax,0
mov [ebp-8],eax
jmp Label@93
Label@92:
mov eax,1
mov [ebp-8],eax
Label@93:
Label@95:
mov eax,[ebp-4]
xor eax,1
test eax,eax
jz Label@94
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push eax
mov eax,[ebp-20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp-20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-8],eax
mov eax,[ebp-20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-4],eax
mov eax,[ebp-20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-20],eax
jmp Label@95
Label@94:
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,36
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-12],eax
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-16],eax
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-16]
push eax
mov eax,[ebp-12]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,36
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-16]
push eax
mov eax,[ebp-12]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@SnakeList@AdjustNode:
push ebp
mov ebp,esp
add esp,-12
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@96
mov eax,1
jmp Label@97
Label@96:
xor eax,eax
Label@97:
pop ebx
test eax,eax
jz Label@98
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@99
Label@98:
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
jne Label@100
mov eax,1
jmp Label@101
Label@100:
xor eax,eax
Label@101:
pop ebx
test eax,eax
jz Label@102
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@103
Label@102:
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
jne Label@104
mov eax,1
jmp Label@105
Label@104:
xor eax,eax
Label@105:
pop ebx
test eax,eax
jz Label@106
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@107
Label@106:
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
Label@107:
Label@103:
Label@99:
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-4],eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-8],eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jge Label@108
mov eax,1
jmp Label@109
Label@108:
xor eax,eax
Label@109:
pop ebx
test eax,eax
jz Label@110
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,17
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
Label@110:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,17
cmp ebx,eax
jle Label@111
mov eax,1
jmp Label@112
Label@111:
xor eax,eax
Label@112:
pop ebx
test eax,eax
jz Label@113
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,0
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
Label@113:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jge Label@114
mov eax,1
jmp Label@115
Label@114:
xor eax,eax
Label@115:
pop ebx
test eax,eax
jz Label@116
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,17
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
Label@116:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,17
cmp ebx,eax
jle Label@117
mov eax,1
jmp Label@118
Label@117:
xor eax,eax
Label@118:
pop ebx
test eax,eax
jz Label@119
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov eax,0
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
Label@119:
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-12],eax
mov eax,[ebp-12]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
jne Label@120
mov eax,1
jmp Label@121
Label@120:
xor eax,eax
Label@121:
pop ebx
test eax,eax
jz Label@122
mov eax,[ecx+20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,1
mov [ecx+40],eax
jmp Label@123
Label@122:
mov eax,[ebp-12]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
jne Label@124
mov eax,1
jmp Label@125
Label@124:
xor eax,eax
Label@125:
pop ebx
test eax,eax
jz Label@126
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
xor eax,eax
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+20]
push ebx
push ecx
mov ebx,eax
mov eax,100
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@127
Label@126:
mov eax,[ebp-12]
push ebx
mov ebx,eax
mov eax,3
cmp ebx,eax
jne Label@128
mov eax,1
jmp Label@129
Label@128:
xor eax,eax
Label@129:
pop ebx
test eax,eax
jz Label@130
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
xor eax,eax
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+20]
push ebx
push ecx
mov ebx,eax
mov eax,500
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+20]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@131
Label@130:
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
Label@131:
Label@127:
Label@123:
mov eax,[ecx+16]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
xor eax,1
test eax,eax
jz Label@132
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
Label@132:
mov eax,0
leave
retn 4
method@SnakeList@UpdateList:
push ebp
mov ebp,esp
add esp,-20
mov eax,[ecx+28]
mov [ebp-12],eax
mov eax,0
mov [ebp-16],eax
mov eax,[ecx+28]
push ebx
mov ebx,eax
mov eax,1
sub ebx,eax
mov eax,ebx
pop ebx
mov [ebp-20],eax
mov eax,[ecx+4]
mov [ebp-4],eax
mov eax,[ecx+32]
push ebx
mov ebx,eax
mov eax,50
cmp ebx,eax
jge Label@133
mov eax,1
jmp Label@134
Label@133:
xor eax,eax
Label@134:
pop ebx
test eax,eax
jz Label@135
mov eax,[ecx+32]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ecx+32],eax
jmp Label@136
Label@135:
mov eax,0
mov [ecx+32],eax
Label@138:
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,[ebp-12]
cmp ebx,eax
jge Label@139
mov eax,1
jmp Label@140
Label@139:
xor eax,eax
Label@140:
pop ebx
test eax,eax
jz Label@137
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@141
mov eax,1
jmp Label@142
Label@141:
xor eax,eax
Label@142:
pop ebx
test eax,eax
jz Label@143
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,0
push eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,40
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+12]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
Label@143:
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,[ebp-20]
cmp ebx,eax
jne Label@144
mov eax,1
jmp Label@145
Label@144:
xor eax,eax
Label@145:
pop ebx
test eax,eax
jz Label@146
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@147
Label@146:
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-8],eax
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
Label@147:
mov eax,[ebp-4]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov [ebp-4],eax
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-16],eax
jmp Label@138
Label@137:
Label@136:
mov eax,0
leave
retn 0
method@SnakeList@Go:
push ebp
mov ebp,esp
add esp,0
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@148
mov eax,1
jmp Label@149
Label@148:
xor eax,eax
Label@149:
pop ebx
test eax,eax
jz Label@150
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
je Label@151
mov eax,1
jmp Label@152
Label@151:
xor eax,eax
Label@152:
pop ebx
test eax,eax
jz Label@153
mov eax,0
mov [ecx+24],eax
Label@153:
jmp Label@154
Label@150:
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
jne Label@155
mov eax,1
jmp Label@156
Label@155:
xor eax,eax
Label@156:
pop ebx
test eax,eax
jz Label@157
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@158
mov eax,1
jmp Label@159
Label@158:
xor eax,eax
Label@159:
pop ebx
test eax,eax
jz Label@160
mov eax,1
mov [ecx+24],eax
Label@160:
jmp Label@161
Label@157:
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
jne Label@162
mov eax,1
jmp Label@163
Label@162:
xor eax,eax
Label@163:
pop ebx
test eax,eax
jz Label@164
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,3
cmp ebx,eax
je Label@165
mov eax,1
jmp Label@166
Label@165:
xor eax,eax
Label@166:
pop ebx
test eax,eax
jz Label@167
mov eax,2
mov [ecx+24],eax
Label@167:
jmp Label@168
Label@164:
mov eax,[ecx+24]
push ebx
mov ebx,eax
mov eax,2
cmp ebx,eax
je Label@169
mov eax,1
jmp Label@170
Label@169:
xor eax,eax
Label@170:
pop ebx
test eax,eax
jz Label@171
mov eax,3
mov [ecx+24],eax
Label@171:
Label@168:
Label@161:
Label@154:
mov eax,0
leave
retn 4
method@SnakeTile@GetXPos:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
leave
retn 8
method@SnakeTile@GetYPos:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
leave
retn 8
method@SnakeTile@SetSnake:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+12]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
lea ebx,[ebx][eax*4]
mov eax,[ebp+16]
mov [ebx],eax
pop ebx
mov eax,0
leave
retn 12
method@SnakeTile@GetSnake:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+12]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
leave
retn 8
method@SnakeTile@DrawItem:
push ebp
mov ebp,esp
add esp,0
mov eax,2
push ebx
mov ebx,eax
mov eax,[ebp+16]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@172
mov eax,1
jmp Label@173
Label@172:
xor eax,eax
Label@173:
pop ebx
test eax,eax
jz Label@174
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
push eax
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
push eax
mov eax,25
push eax
mov eax,25
push eax
mov eax,[ecx+16]
push eax
call minij_runtime_minijava_bitblt
jmp Label@175
Label@174:
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
push eax
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp+12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp+8]
add eax,ebx
pop ebx
mov eax,[ebx][eax*4]
pop ebx
push eax
mov eax,25
push eax
mov eax,25
push eax
mov eax,[ecx+20]
push eax
call minij_runtime_minijava_bitblt
Label@175:
mov eax,0
leave
retn 12
method@SnakeTile@SnakeDraw:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@SnakeTile@InitSnake:
push ebp
mov ebp,esp
add esp,-8
lea eax,[ecx+24]
push eax
mov eax,255
push eax
mov eax,16
push eax
mov eax,74
push eax
mov eax,238
push eax
call minij_runtime_minijava_color
lea eax,[ecx+28]
push eax
mov eax,255
push eax
mov eax,2
push eax
mov eax,156
push eax
mov eax,239
push eax
call minij_runtime_minijava_color
mov eax,25
push ebx
push edx
mov ebx,eax
mov eax,25
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+16],eax
mov eax,25
push ebx
push edx
mov ebx,eax
mov eax,25
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+20],eax
mov eax,18
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+12],eax
mov eax,0
mov [ebp-4],eax
mov eax,[ecx+16]
mov eax,[eax-4]
mov [ebp-8],eax
Label@177:
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,[ebp-8]
cmp ebx,eax
jge Label@178
mov eax,1
jmp Label@179
Label@178:
xor eax,eax
Label@179:
pop ebx
test eax,eax
jz Label@176
mov eax,[ecx+16]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ecx+24]
mov [ebx],eax
pop ebx
mov eax,[ecx+20]
push ebx
mov ebx,eax
mov eax,[ebp-4]
lea ebx,[ebx][eax*4]
mov eax,[ecx+28]
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-4],eax
jmp Label@177
Label@176:
lea eax,[ecx+32]
push eax
call minij_runtime_minijava_rand
mov eax,8
push ebx
mov ebx,eax
mov eax,[ecx+32]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,5
add eax,ebx
pop ebx
mov [ebp-4],eax
lea eax,[ecx+32]
push eax
call minij_runtime_minijava_rand
mov eax,8
push ebx
mov ebx,eax
mov eax,[ecx+32]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,5
add eax,ebx
pop ebx
mov [ebp-8],eax
lea eax,[ecx+32]
push eax
call minij_runtime_minijava_rand
mov eax,4
push ebx
mov ebx,eax
mov eax,[ecx+32]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
mov [ecx+32],eax
push ebx
push 44
call minij_runtime_alloc
mov ebx,offset table@SnakeList
mov [eax],ebx
pop ebx
mov [ecx+36],eax
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,ecx
push eax
mov eax,[ecx+32]
push eax
mov eax,[ebp-8]
push eax
mov eax,[ebp-4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,16
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@SnakeTile@Init:
push ebp
mov ebp,esp
add esp,-16
mov eax,18
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+4],eax
mov eax,18
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+8],eax
mov eax,25
mov [ebp-4],eax
mov eax,25
mov [ebp-8],eax
mov eax,0
mov [ebp-12],eax
mov eax,0
mov [ebp-16],eax
Label@181:
mov eax,[ebp-12]
push ebx
mov ebx,eax
mov eax,18
cmp ebx,eax
jge Label@182
mov eax,1
jmp Label@183
Label@182:
xor eax,eax
Label@183:
pop ebx
test eax,eax
jz Label@180
mov eax,0
mov [ebp-16],eax
mov eax,25
mov [ebp-4],eax
Label@185:
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,18
cmp ebx,eax
jge Label@186
mov eax,1
jmp Label@187
Label@186:
xor eax,eax
Label@187:
pop ebx
test eax,eax
jz Label@184
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,[ebp-12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-16]
add eax,ebx
pop ebx
lea ebx,[ebx][eax*4]
mov eax,[ebp-4]
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,25
add eax,ebx
pop ebx
mov [ebp-4],eax
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-12]
push ebx
push edx
mov ebx,eax
mov eax,18
imul ebx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-16]
add eax,ebx
pop ebx
lea ebx,[ebx][eax*4]
mov eax,[ebp-8]
mov [ebx],eax
pop ebx
mov eax,[ebp-16]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-16],eax
jmp Label@185
Label@184:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,25
add eax,ebx
pop ebx
mov [ebp-8],eax
mov eax,[ebp-12]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-12],eax
jmp Label@181
Label@180:
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,32
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@SnakeTile@Update:
push ebp
mov ebp,esp
add esp,-4
mov eax,0
mov [ebp-4],eax
lea eax,[ebp-4]
push eax
mov eax,39
push eax
call minij_runtime_minijava_getkeystate
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@188
mov eax,1
jmp Label@189
Label@188:
xor eax,eax
Label@189:
pop ebx
test eax,eax
jz Label@190
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,0
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@191
Label@190:
lea eax,[ebp-4]
push eax
mov eax,37
push eax
call minij_runtime_minijava_getkeystate
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@192
mov eax,1
jmp Label@193
Label@192:
xor eax,eax
Label@193:
pop ebx
test eax,eax
jz Label@194
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,1
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@195
Label@194:
lea eax,[ebp-4]
push eax
mov eax,38
push eax
call minij_runtime_minijava_getkeystate
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@196
mov eax,1
jmp Label@197
Label@196:
xor eax,eax
Label@197:
pop ebx
test eax,eax
jz Label@198
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,2
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
jmp Label@199
Label@198:
lea eax,[ebp-4]
push eax
mov eax,40
push eax
call minij_runtime_minijava_getkeystate
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
je Label@200
mov eax,1
jmp Label@201
Label@200:
xor eax,eax
Label@201:
pop ebx
test eax,eax
jz Label@202
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov eax,3
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,20
call dword ptr [eax]
pop ecx
pop ebx
Label@202:
Label@199:
Label@195:
Label@191:
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
xor eax,1
test eax,eax
jz Label@203
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,24
call dword ptr [eax]
pop ecx
pop ebx
Label@203:
mov eax,0
leave
retn 0
method@GameMain@Draw:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+32]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,12
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,28
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+4]
push eax
mov eax,1
push eax
call minij_runtime_minijava_present
mov eax,0
leave
retn 0
method@GameMain@Init:
push ebp
mov ebp,esp
add esp,-8
mov eax,0
push eax
call minij_runtime_minijava_srand
lea eax,[ecx+4]
push eax
mov eax,255
push eax
mov eax,235
push eax
mov eax,178
push eax
mov eax,0
push eax
call minij_runtime_minijava_color
lea eax,[ecx+12]
push eax
mov eax,255
push eax
mov eax,41
push eax
mov eax,161
push eax
mov eax,59
push eax
call minij_runtime_minijava_color
lea eax,[ecx+16]
push eax
mov eax,255
push eax
mov eax,17
push eax
mov eax,217
push eax
mov eax,255
push eax
call minij_runtime_minijava_color
lea eax,[ecx+20]
push eax
mov eax,255
push eax
mov eax,13
push eax
mov eax,110
push eax
mov eax,173
push eax
call minij_runtime_minijava_color
lea eax,[ecx+24]
push eax
mov eax,255
push eax
mov eax,16
push eax
mov eax,74
push eax
mov eax,238
push eax
call minij_runtime_minijava_color
lea eax,[ecx+28]
push eax
mov eax,255
push eax
mov eax,2
push eax
mov eax,156
push eax
mov eax,239
push eax
call minij_runtime_minijava_color
mov eax,50
push ebx
push edx
mov ebx,eax
mov eax,50
imul ebx
pop edx
pop ebx
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ecx+8],eax
mov eax,[ecx+8]
mov eax,[eax-4]
mov [ebp-4],eax
mov eax,0
mov [ebp-8],eax
Label@205:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,[ebp-4]
cmp ebx,eax
jge Label@206
mov eax,1
jmp Label@207
Label@206:
xor eax,eax
Label@207:
pop ebx
test eax,eax
jz Label@204
mov eax,50
push ebx
mov ebx,eax
mov eax,[ebp-8]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@208
mov eax,1
jmp Label@209
Label@208:
xor eax,eax
Label@209:
pop ebx
push ebx
mov ebx,eax
mov eax,50
push ebx
mov ebx,eax
mov eax,[ebp-8]
push edx
cdq
idiv ebx
mov eax,edx
pop edx
pop ebx
push ebx
mov ebx,eax
mov eax,49
cmp ebx,eax
jne Label@210
mov eax,1
jmp Label@211
Label@210:
xor eax,eax
Label@211:
pop ebx
or eax,ebx
pop ebx
test eax,eax
jz Label@212
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-8]
lea ebx,[ebx][eax*4]
mov eax,[ecx+16]
mov [ebx],eax
pop ebx
jmp Label@213
Label@212:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jl Label@214
mov eax,1
jmp Label@215
Label@214:
xor eax,eax
Label@215:
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,49
cmp ebx,eax
jg Label@216
mov eax,1
jmp Label@217
Label@216:
xor eax,eax
Label@217:
pop ebx
and eax,ebx
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,2450
cmp ebx,eax
jl Label@218
mov eax,1
jmp Label@219
Label@218:
xor eax,eax
Label@219:
pop ebx
push ebx
mov ebx,eax
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,2499
cmp ebx,eax
jg Label@220
mov eax,1
jmp Label@221
Label@220:
xor eax,eax
Label@221:
pop ebx
and eax,ebx
pop ebx
or eax,ebx
pop ebx
test eax,eax
jz Label@222
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-8]
lea ebx,[ebx][eax*4]
mov eax,[ecx+24]
mov [ebx],eax
pop ebx
jmp Label@223
Label@222:
mov eax,[ecx+8]
push ebx
mov ebx,eax
mov eax,[ebp-8]
lea ebx,[ebx][eax*4]
mov eax,[ecx+20]
mov [ebx],eax
pop ebx
Label@223:
Label@213:
mov eax,[ebp-8]
push ebx
mov ebx,eax
mov eax,1
add eax,ebx
pop ebx
mov [ebp-8],eax
jmp Label@205
Label@204:
push ebx
push 20
call minij_runtime_alloc
mov ebx,offset table@WallTile
mov [eax],ebx
pop ebx
mov [ecx+32],eax
mov eax,[ecx+32]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
push ebx
push 40
call minij_runtime_alloc
mov ebx,offset table@SnakeTile
mov [eax],ebx
pop ebx
mov [ecx+36],eax
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@GameMain@Update:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+36]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,24
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 0
method@Game@Delay:
push ebp
mov ebp,esp
add esp,0
mov eax,1
push eax
call minij_runtime_minijava_wait
mov eax,0
leave
retn 4
method@MiniDraw@MessageHandler:
method@Game@MessageHandler:
push ebp
mov ebp,esp
add esp,0
mov eax,[ecx+4]
push ebx
mov ebx,eax
mov eax,0
cmp ebx,eax
jne Label@224
mov eax,1
jmp Label@225
Label@224:
xor eax,eax
Label@225:
pop ebx
test eax,eax
jz Label@226
mov eax,1
mov [ecx+4],eax
push ebx
push 40
call minij_runtime_alloc
mov ebx,offset table@GameMain
mov [eax],ebx
pop ebx
mov [ecx+8],eax
mov eax,[ecx+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,0
call dword ptr [eax]
pop ecx
pop ebx
Label@226:
mov eax,[ecx+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,8
call dword ptr [eax]
pop ecx
pop ebx
mov eax,[ecx+8]
push ebx
push ecx
mov ebx,eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,0
mov eax,[ebx][eax*4]
pop ebx
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
mov eax,0
leave
retn 8
method@Window@Run:
push ebp
mov ebp,esp
add esp,-8
mov eax,35
push ebx
push edx
push eax
mov ebx,4
inc eax
mul ebx
push eax
call minij_runtime_alloc
pop ebx
mov [eax],ebx
add eax,4
pop edx
pop ebx
mov [ebp-4],eax
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,0
lea ebx,[ebx][eax*4]
mov eax,77
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,1
lea ebx,[ebx][eax*4]
mov eax,105
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,2
lea ebx,[ebx][eax*4]
mov eax,110
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,3
lea ebx,[ebx][eax*4]
mov eax,105
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,4
lea ebx,[ebx][eax*4]
mov eax,74
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,5
lea ebx,[ebx][eax*4]
mov eax,97
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,6
lea ebx,[ebx][eax*4]
mov eax,118
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,7
lea ebx,[ebx][eax*4]
mov eax,97
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,8
lea ebx,[ebx][eax*4]
mov eax,32
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,9
lea ebx,[ebx][eax*4]
mov eax,83
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,10
lea ebx,[ebx][eax*4]
mov eax,110
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,11
lea ebx,[ebx][eax*4]
mov eax,97
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,12
lea ebx,[ebx][eax*4]
mov eax,107
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,13
lea ebx,[ebx][eax*4]
mov eax,101
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,14
lea ebx,[ebx][eax*4]
mov eax,32
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,15
lea ebx,[ebx][eax*4]
mov eax,45
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,16
lea ebx,[ebx][eax*4]
mov eax,80
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,17
lea ebx,[ebx][eax*4]
mov eax,111
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,18
lea ebx,[ebx][eax*4]
mov eax,119
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,19
lea ebx,[ebx][eax*4]
mov eax,101
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,20
lea ebx,[ebx][eax*4]
mov eax,114
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,21
lea ebx,[ebx][eax*4]
mov eax,101
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,22
lea ebx,[ebx][eax*4]
mov eax,100
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,23
lea ebx,[ebx][eax*4]
mov eax,32
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,24
lea ebx,[ebx][eax*4]
mov eax,98
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,25
lea ebx,[ebx][eax*4]
mov eax,121
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,26
lea ebx,[ebx][eax*4]
mov eax,32
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,27
lea ebx,[ebx][eax*4]
mov eax,68
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,28
lea ebx,[ebx][eax*4]
mov eax,68
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,29
lea ebx,[ebx][eax*4]
mov eax,114
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,30
lea ebx,[ebx][eax*4]
mov eax,77
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,31
lea ebx,[ebx][eax*4]
mov eax,83
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,32
lea ebx,[ebx][eax*4]
mov eax,100
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,33
lea ebx,[ebx][eax*4]
mov eax,111
mov [ebx],eax
pop ebx
mov eax,[ebp-4]
push ebx
mov ebx,eax
mov eax,34
lea ebx,[ebx][eax*4]
mov eax,115
mov [ebx],eax
pop ebx
push ebx
push 12
call minij_runtime_alloc
mov ebx,offset table@Game
mov [eax],ebx
pop ebx
push eax
mov eax,[ebp-4]
push eax
mov eax,500
push eax
mov eax,500
push eax
call minij_runtime_minijava_window
mov eax,0
leave
retn 0
end  main@Snake
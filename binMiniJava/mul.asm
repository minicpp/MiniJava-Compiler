;This asm generate from MiniJava Parser.Present by HanDong:)
.386
.model flat,stdcall
option casemap:none
include minijava.inc
includelib minijava.lib
.const
@version@ db 'MiniJava 0.0.1 Alpha Powered by DDRMSDOS (minicpp)',0
table@Fac  \
dd  method@Fac@Run
dd  method@Fac@ComputeFac
.code
main@Factorial:
push ebx
push 4
call minij_runtime_alloc
mov ebx,offset table@Fac
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
method@Fac@Run:
push ebp
mov ebp,esp
add esp,-4
lea eax,[ebp-4]
push eax
call minij_runtime_minijava_scanf
mov eax,ecx
push ebx
push ecx
mov ebx,eax
mov eax,[ebp-4]
push eax
mov ecx,ebx
mov eax,[ecx]
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
leave
retn 0
method@Fac@ComputeFac:
push ebp
mov ebp,esp
add esp,-4
mov eax,[ebp+8]
push ebx
mov ebx,eax
mov eax,1
cmp ebx,eax
jge Label@0
mov eax,1
jmp Label@1
Label@0:
xor eax,eax
Label@1:
pop ebx
test eax,eax
jz Label@2
mov eax,1
mov [ebp-4],eax
jmp Label@3
Label@2:
mov eax,[ebp+8]
push ebx
push edx
mov ebx,eax
mov eax,ecx
push ebx
push ecx
mov ebx,eax
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
add eax,4
call dword ptr [eax]
pop ecx
pop ebx
imul ebx
pop edx
pop ebx
mov [ebp-4],eax
Label@3:
mov eax,[ebp-4]
leave
retn 4
end  main@Factorial
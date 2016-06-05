;**********************************************
; Powered by DDRMSDOS
; HanDong Software Studio
; E-mail: ddrmsdos@163.com
; MiniJava Runtime Library
; Version: 1.0
; Date:    2008.5.5
; minijava.dll
;**********************************************
minij_runtime_system_out_println proto :dword

minij_runtime_minijava_print proto :dword

minij_runtime_minijava_scanf proto :dword

minij_runtime_exit proto

minij_runtime_alloc proto :dword

minij_runtime_minijava_window  proto :dword, :dword ,:dword ,:dword
minij_runtime_minijava_wait    proto :dword
minij_runtime_minijava_getkeystate proto :dword, :dword
minij_runtime_minijava_bitblt proto :dword,:dword,:dword,:dword,:dword
minij_runtime_minijava_color  proto :dword,:dword,:dword,:dword,:dword
minij_runtime_minijava_present proto :dword,:dword
minij_runtime_minijava_srand  proto :dword
minij_runtime_minijava_rand   proto :dword
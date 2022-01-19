	.text
	.file	"builtin.c"
	.globl	_f_print                # -- Begin function _f_print
	.p2align	2
	.type	_f_print,@function
_f_print:                               # @_f_print
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	_f_print, .Lfunc_end0-_f_print
	.cfi_endproc
                                        # -- End function
	.globl	_f_println              # -- Begin function _f_println
	.p2align	2
	.type	_f_println,@function
_f_println:                             # @_f_println
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	_f_println, .Lfunc_end1-_f_println
	.cfi_endproc
                                        # -- End function
	.globl	_f_printInt             # -- Begin function _f_printInt
	.p2align	2
	.type	_f_printInt,@function
_f_printInt:                            # @_f_printInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	_f_printInt, .Lfunc_end2-_f_printInt
	.cfi_endproc
                                        # -- End function
	.globl	_f_printlnInt           # -- Begin function _f_printlnInt
	.p2align	2
	.type	_f_printlnInt,@function
_f_printlnInt:                          # @_f_printlnInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	_f_printlnInt, .Lfunc_end3-_f_printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	_f_getInt               # -- Begin function _f_getInt
	.p2align	2
	.type	_f_getInt,@function
_f_getInt:                              # @_f_getInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	addi	a1, sp, 8
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	_f_getInt, .Lfunc_end4-_f_getInt
	.cfi_endproc
                                        # -- End function
	.globl	_f_toString             # -- Begin function _f_toString
	.p2align	2
	.type	_f_toString,@function
_f_toString:                            # @_f_toString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	addi	a0, zero, 13
	mv	a1, zero
	call	malloc
	lw	a2, 8(sp)
	sw	a0, 0(sp)
	lui	a1, %hi(.L.str.1)
	addi	a1, a1, %lo(.L.str.1)
	call	sprintf
	lw	a0, 0(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	_f_toString, .Lfunc_end5-_f_toString
	.cfi_endproc
                                        # -- End function
	.globl	_f_getString            # -- Begin function _f_getString
	.p2align	2
	.type	_f_getString,@function
_f_getString:                           # @_f_getString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	addi	a0, zero, 1024
	mv	a1, zero
	call	malloc
	mv	a1, a0
	sw	a0, 8(sp)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	__isoc99_scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	_f_getString, .Lfunc_end6-_f_getString
	.cfi_endproc
                                        # -- End function
	.globl	_f__malloc              # -- Begin function _f__malloc
	.p2align	2
	.type	_f__malloc,@function
_f__malloc:                             # @_f__malloc
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	srai	a1, a0, 31
	call	malloc
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	_f__malloc, .Lfunc_end7-_f__malloc
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_splice          # -- Begin function _f__str_splice
	.p2align	2
	.type	_f__str_splice,@function
_f__str_splice:                         # @_f__str_splice
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	sw	a0, 16(sp)
	sw	a1, 8(sp)
	call	strlen
	lw	a2, 8(sp)
	mv	s0, a0
	mv	s1, a1
	mv	a0, a2
	call	strlen
	add	a1, s1, a1
	add	a2, s0, a0
	sltu	a0, a2, s0
	add	a1, a1, a0
	addi	a0, a2, 2
	sltu	a2, a0, a2
	add	a1, a1, a2
	call	malloc
	lw	a1, 16(sp)
	sw	a0, 0(sp)
	call	strcpy
	lw	a0, 0(sp)
	lw	a1, 8(sp)
	call	strcat
	lw	a0, 0(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end8:
	.size	_f__str_splice, .Lfunc_end8-_f__str_splice
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_eq              # -- Begin function _f__str_eq
	.p2align	2
	.type	_f__str_eq,@function
_f__str_eq:                             # @_f__str_eq
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end9:
	.size	_f__str_eq, .Lfunc_end9-_f__str_eq
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_ne              # -- Begin function _f__str_ne
	.p2align	2
	.type	_f__str_ne,@function
_f__str_ne:                             # @_f__str_ne
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	_f__str_ne, .Lfunc_end10-_f__str_ne
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_lt              # -- Begin function _f__str_lt
	.p2align	2
	.type	_f__str_lt,@function
_f__str_lt:                             # @_f__str_lt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	_f__str_lt, .Lfunc_end11-_f__str_lt
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_le              # -- Begin function _f__str_le
	.p2align	2
	.type	_f__str_le,@function
_f__str_le:                             # @_f__str_le
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end12:
	.size	_f__str_le, .Lfunc_end12-_f__str_le
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_gt              # -- Begin function _f__str_gt
	.p2align	2
	.type	_f__str_gt,@function
_f__str_gt:                             # @_f__str_gt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	sgtz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	_f__str_gt, .Lfunc_end13-_f__str_gt
	.cfi_endproc
                                        # -- End function
	.globl	_f__str_ge              # -- Begin function _f__str_ge
	.p2align	2
	.type	_f__str_ge,@function
_f__str_ge:                             # @_f__str_ge
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	sw	a1, 0(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	_f__str_ge, .Lfunc_end14-_f__str_ge
	.cfi_endproc
                                        # -- End function
	.globl	_class_string_length    # -- Begin function _class_string_length
	.p2align	2
	.type	_class_string_length,@function
_class_string_length:                   # @_class_string_length
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	call	strlen
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	_class_string_length, .Lfunc_end15-_class_string_length
	.cfi_endproc
                                        # -- End function
	.globl	_class_string_substring # -- Begin function _class_string_substring
	.p2align	2
	.type	_class_string_substring,@function
_class_string_substring:                # @_class_string_substring
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	.cfi_offset ra, -4
	sw	a0, 24(sp)
	sw	a1, 20(sp)
	sw	a2, 16(sp)
	sub	a0, a2, a1
	addi	a0, a0, 1
	sw	a0, 12(sp)
	srai	a1, a0, 31
	call	malloc
	lw	a1, 24(sp)
	lw	a2, 20(sp)
	lw	a3, 16(sp)
	sw	a0, 8(sp)
	add	a1, a1, a2
	sub	a2, a3, a2
	call	memcpy
	lw	a0, 8(sp)
	lw	a1, 12(sp)
	add	a0, a1, a0
	sb	zero, -1(a0)
	lw	a0, 8(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end16:
	.size	_class_string_substring, .Lfunc_end16-_class_string_substring
	.cfi_endproc
                                        # -- End function
	.globl	_class_string_parseInt  # -- Begin function _class_string_parseInt
	.p2align	2
	.type	_class_string_parseInt,@function
_class_string_parseInt:                 # @_class_string_parseInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	.cfi_offset ra, -4
	sw	a0, 8(sp)
	lui	a1, %hi(.L.str.1)
	addi	a1, a1, %lo(.L.str.1)
	addi	a2, sp, 4
	call	__isoc99_sscanf
	lw	a0, 4(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	_class_string_parseInt, .Lfunc_end17-_class_string_parseInt
	.cfi_endproc
                                        # -- End function
	.globl	_class_string_ord       # -- Begin function _class_string_ord
	.p2align	2
	.type	_class_string_ord,@function
_class_string_ord:                      # @_class_string_ord
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	a0, 8(sp)
	sw	a1, 4(sp)
	add	a0, a0, a1
	lb	a0, 0(a0)
	addi	sp, sp, 16
	ret
.Lfunc_end18:
	.size	_class_string_ord, .Lfunc_end18-_class_string_ord
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s\n"
	.size	.L.str, 4

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%d"
	.size	.L.str.1, 3

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%d\n"
	.size	.L.str.2, 4

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%s"
	.size	.L.str.3, 3

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits

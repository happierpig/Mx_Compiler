# Toyslator

A toy Compiler supports compiling single file code in the simple programing language Mx* into RISC32I assembly. CS2966 Course Project.



#### Feature

- [x] Use ANTLR to analyse source code
- [x] Construct AST from CST to do Semantic Check
- [x] Implement IR confirming to LLVM form(executable by clang)
- [x] Adopt Memory2Reg, a small optimization
- [x] GraphColoring register allocation
- [ ] More advanced Instruction selector
- [ ] More complex Optimization, like SCCP ADCB and so on
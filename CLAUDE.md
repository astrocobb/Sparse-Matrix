# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Java assignment implementing a **SparseMatrix** class that stores integer matrices in a memory-efficient sparse format (only non-default values are stored with their row/column positions).

## Build & Run

This is an IntelliJ IDEA project. Source files are in `SparseMatrix/src/`. There is no build tool (Maven/Gradle) — compile and run directly:

```bash
cd SparseMatrix/src
javac *.java
java SparseMatrix
```

Note: The `main` method uses instance method syntax (`void main` without `static`) and references an `IO` utility class (not included in this repo — likely provided by the course).

## Architecture

- **`SparseMatrix`** (`SparseMatrix/src/SparseMatrix.java`): Core class storing `rows`, `cols`, `defaultValue`, and a `String[]` of non-default values. Contains constructor (converts dense matrix to sparse), `outputToMatrix` (converts back), and test cases in `main`.
- **`NonDefaultValueData`** (`SparseMatrix/src/NonDefaultValueData.java`): Simple data class holding `value`, `row`, `col` for each non-zero entry.

## Assignment Requirements (from sparse_instructions.txt)

Required methods: constructor from `int[][]`, `outputToMatrix`, static `matricesAreEqual`, assert in constructor verifying round-trip correctness. Three test matrices required (4x4, 10x10, 20x20). One bonus option must be chosen (getSavings, add, or non-zero default support).

## Current State

Work in progress — `outputToMatrix` is stubbed out, `matricesAreEqual` is not yet implemented, assert verification is missing, and only the first test case exists.

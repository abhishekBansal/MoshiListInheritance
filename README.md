# MoshiListInheritance
Reproduces issue in generated adapter with moshi and inheritance hierarchy in List

Generated Adapter fails to compile with following error.
```
Type mismatch: inferred type is List<OptionV2>? but List<SelectionOption>? was expected
```

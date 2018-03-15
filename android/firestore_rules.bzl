def fire():
  native.py_binary(
      name = "firestore_genrule_py_impl",
      srcs = ["firestore_genrule_py_impl.py"],
      data = ["google-services.json"],
  )

  native.genrule(
      name = "firestore-genrule",
      outs = ["FirestoreConfig.java"],
      cmd = "python android/firestore_genrule_py_impl.py > $(@)",
      tools = [
          "google-services.json",
          ":firestore_genrule_py_impl",
      ],
  )
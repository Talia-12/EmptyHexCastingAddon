# Empty Hex Casting Addon Framework

This is a stripped-down template for 1.92.2 Fabric+Forge Hex Casting addons.  It's heavily derived (aka, taken wholesale) from [HexCasting](https://www.curseforge.com/minecraft/mc-mods/hexcasting)'s excellent project layout, and was created by stripping everything from [Hexal](https://modrinth.com/mod/hexal)'s repository that wasn't relevant.

This has all the dependencies _necessary_ for functioning as an addon, as well as the dependencies and code for a config system that will work for both forge and fabric. There is an example Action, and an example Spell, and you can look at the existing HexCasting or Hexal code to see how actual Actions and Spells are written.

The approach here does not actually avoid the various complexities of cross-modloader programming.  You still need to very carefully maintain functions that are distinct between the two environments.  It's just a very well-designed approach for separating these fields, without having to duplicate a lot of work.

There will be some follow-up work to turn it from a generic "Hello World" mod into _your_ Generic "Hello World" mod.  Some keywords to look at:

``yourmod.name.here`` is the default package name.  While valid for test purposes, you _will_ need to change this before release (and ideally immediately after forking) or risk (near-certain) collisions and annoying bugs, and it's easier the earlier you change it.
IntelliJ (and most other IDEAs) will allow bulk refactoring, though expect to need to change a few ``package`` settings at the top of files.

See [this page](https://docs.oracle.com/javase/specs/jls/se6/html/packages.html#7.7) for information about package naming rules, particularly _don't use a popular TLD that you don't actually own or control_.

``yourmod`` is the default modID.  _AFTER_ you've refactored your package name, this is the next thing to change.  It's only referenced in a few places, but other mods, compile-time behavior (both Forge and Fabric) and maven will interact with it heavily.

See [this page](https://maven.apache.org/guides/mini/guide-naming-conventions.html) for some naming conventions specific to this ID.

After you've made these changes, it's safe to leave ``YourAPI`` and ``YourConfig`` as their current names, but do feel free to rename them; something specific to your modId or your package will make regularly importing them less obnoxious.

``Your Mod`` is the default mod name.  This is more for aesthetics than code relevance, but it does show up in a good few places and it's kinda embarrassing if it's left at default.

Check your ``mods.toml`` (for Forge) and ``fabric.mod.json`` (for Fabric) regarding other mod-specific settings, such as author, icon, dependencies you want presented to the user, so on.  There's a lot I'm not going to summarize here.

Actually Running:

Once you've made those updates, you'll need to reload Gradle.  Do it a couple of times, for safety's sake.

The automatically generated Run configurations won't work.  Sorry.  You'll need to use Gradle->Fabric>Tasks->Fabric->run* or Gradle->Forge->Tasks->forgegradle runs->run*.  There's probably a way to fix this, but good luck.
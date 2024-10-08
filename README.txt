-----------------------------------------------------------------------------------------------
My Discord is Wojak#9058, YT is https://www.youtube.com/@wojak6378, or contact me on Curseforge/Modrinith in the comments section/DMS if you wish contact me. 
-----------------------------------------------------------------------------------------------

Hello, I am the sole developer of this fork(along with rrogalski for his help with grabbing the assets and workspace cleanup) that started development back in late Summer of 2021 and has come a long, long way from what it was over 3 years ago. I have always been with the aether mod since I originally saw paulsoaresjr's let's play of it on youtube back in 2011. I always loved the mod and went on to become a regular member on the official aether server back during the 1.6.4-1.7.10 era and eventually Craftland since that was the only multiplayer port of Aether I at the time. I originally started this fork due to being irritated of the aether mod keeping on being redone every couple major versions (Aether Legacy -> Aether II -> Aether Highlands) instead of expanding upon what was there. This fork aims to expand upon the orginal aether mod with brand new features or features like ones from Aether II, Highlands, or Aether Extended which complement the orginal mod; think of this as a revision or alternate timeline like Better than Adventure for 1.7.3 beta or the Better than Wolves mod or in more simplistic terms as Aether 1.5. I will be continuing the mod in the future as still have additions and changes I will like to implement.

I must give a special thanks to the Delirus for the code they provided me that got the caves generating correctly and help troubleshooting. Also, thanks to the 1.7.10 modding community for feedback on bugs and features. Also MAJOR thanks to rrogalski for his help creating the code to grab the assets as well as helping fix up the workspace, the mod would of never been released otherwise.

------------------------------------------------------------------------------------------------------------
For the new amplifier structure there are 2 png files in the jar file that show how to build the structure.
------------------------------------------------------------------------------------------------------------
Most of the changes are listed in this google doc link below.

Link to the new additions/changes(Probably missed many)
https://docs.google.com/document/d/1fkVXPPqPQACXHQv0xvcLovIPfzGFf37ymhcmiVsBZSM/edit?usp=sharing

Source is on GitHub

------------------------------------------------------------------------
Those who wish to contribute to the mod, here are some things to look at.
------------------------------------------------------------------------

Major Bugs
-----------
Gloves not displaying in 1st person.(This is due to some issue with many performance mods like Optfine and Angelica, standalone it works)
Parachute code needs to be rewritten to not be so buggy.(Fixed the crash that made parachutes basically usuable(especially on servers) for the length legacy has been released)

Known issues
------------
Tie most accessories like gloves and capes to be damaged only when the player is, not at random.
New items with extended reach sometimes being buggy and refusing to work correctly when either breaking blocks and hitting mobs further away.(The code for it is tied in multiple classes making it quite confusing to add on to and replicate)
New shields having a renderer error when shifting
Aerclouds not always negating fall damage if the player is falling too fast? (I have not encountered this bug but others have)
Several textures are not to my liking right now and will be improved upon in the future.

Biome integration
-----------------
Would like to have actual biomes like how they work in the base game but have not have had luck getting them to work...


Other mod integration
----------------------
Having machines such as the enchanter, freezer, and amplifier show recipes in NEI

DONE - Baubles support has been added thanks to jss2a98aj

Tinkers support?

Other notes
-----------
If you see anything that is being done twice or legacy classes I forgot to delete, go ahead, and clean it up.
If you have an idea or feature you would like to add go ahead and ask me if I approve of it.
I will not be adding in major concepts from the modern aether or its addons especially new features from the upcoming Aether II whenever that releases or concepts like the platinum dungeon in lost content.

-----------------------------------------------
LICENSING COMMENTS
-----------------------------------------------
This project uses various code used from several sources.

This project is based on and a fork of the Aether Legacy, by the Aether Team,
released under LGPL-3. Thanks to the Aether Team and all contributors!!!!
https://github.com/The-Aether-Team/The-Aether-Archived/tree/1.7.10

This project also uses a lot of code from the Aether II, also by the Aether
Team and also released under the LGPL-3. 
https://github.com/The-Aether-Team/The-Aether-II

While we don't use any code or redistribute assets from the Aether II for
version 1.7.10, we still want to thank Gilded Games for the inspiration and
assets used at runtime. All Rights Reserved.

To our best knowledge, this project does not redistribute any code or assets
we do not have the rights to, and is fully compliant with upstream licenses.
If you find us to be accidentally redistributig content we do not have the
right to, or somehow violating the licenses of used code, please inform us so
we can address them right away. 

If you are an upstream project, we strive to maintain license compatability
and are more than happy to contribute any fixes or improvements. We are happy
to license any original code under any license to do so.

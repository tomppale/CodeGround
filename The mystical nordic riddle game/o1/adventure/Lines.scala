package o1.adventure

class Lines:
  // Mimir's lines
  val intro = "\nThe mystical nordic adventure\n"+"-"*29+"\nOne day you wonder around the library looking for something interesting to read.\nSuddenly you notice a book you haven't seen before \"The mystical nordic\". You reach for it.\nYou put it on table blowing the dust off of it and open the book. It's a foreign language but you're able to read the first line: To... Valhalla\nSuddenly a light appears from the book and your sight is blinded. As the light fades away you find yourself in a unknown land. \nYou hear a voice behind you\n\n"
  val mimirIntro = "You see a severed head in a pedestal and take a fright. Suddenly you hear a calming voice and realize it coming from the head.\nMimir:\nHello there stranger let me introduce myself. It is I Mimir, the smartest man among the realms. \nAlthough maybe more appropriate term would be smartest head among realms. Ehhh long story my friend\n"
  val start = "Mimir:\nWell... enough of me aye? By request of goddess Freya, I'm assigned to help you during your journey and get you back to your world. \nAs smartest man alive I believe I can be quite an asset aye? So anytime you need anything, just call the great Mimir! (input: \"help\" or \"Mimir\")\n\nStill confused you come to a conclusion that any help is better than nothing and start your journey with severed head hanging from your waist\""
  val afterInvalidCommand = s"Mimir:\nI'm not quite sure what you're trying to say ehh. \nIf you need any help just say so my friend (input: \"help\" or \"Mimir\")"

  // Mimir's lines about realms
  val mimirMidgard = "Mimir: \nMidgard or Earth according to you lads, the center of all and where life blossoms. \nAlways wondered why gods wanted to live in Asgard, I think it's much livelier in here."
  val mimirAlfheim = "Mimir: \nAh Alfheim, I sure hope you brought something to cover yer eyes with cause home of light elves have never seen dark days"
  val mimirAsgard = "Mimir: \nAnd here's the legendary Asgard you have heard so much about. Like it? Well Don't get yer hopes up. \nIt has seen better days and those fantasies you see in these... Marvel-films? Are just that... fantasies."
  val mimirMuspelheim = "Mimir: \nThe Blazing realm or that what it feels like. Prophecy says that the fire giant from this realm Surtr will bring Ragnarok to Asgard which will shake balance in all realms. \nYou don't want to be here when it happens lad."
  val mimirHelheim = "Mimir: \nThe kingdom of dead. Eternal hunger and sickness awaits for those who didn't die warriors way and earn their way to Valhalla.\nAll Father's cold heart surely knows no bounds... bloody picker."
  val mimirSvartalfheim = "Mimir: \nSvartalfheim. The dark aura brings chills to my back lad or it would if I still had a back. \nWhatever you came to get from here, get it and let's get out of here aye?"
  val mimirNiflheim = "Mimir: \nOh bollocks I'm going to have constant brain freeze here. Niflheim the realm with never ending blizzard and will bring the new ice age to midgard \nwhen Ragnarok begins. Actually does \"Oh bollocks\" even carry any value cause bollocks are kinda non-existent in this cold"
  val mimirJotunheim = "Mimir: \nRealm of the giants. At least you won't see Thor here, he is the most unwelcome god here I wonder if my well is still intact, oh well nevermind that. \nSee that enormous temple over there? That is your ticket home my friend."
  val mimirVanaheim = "Mimir: \nWell now I'm in piece. The realm of fertility, purity and wisdom. \nWho's that over there...? Aah lad, that's Sinmara a beautiful giantness and Surtr's beloved"
  val mimirTempleEntrance = "Mimir:\nAhh the legendary Temple of Ymir the first giant. Imagine you grew under his arm.\nNorse mythology is confusing ehh my friend? Heheh well enough of that. Behind this temple's door is our goal, so let's get it open aye?"

  // Mimir's help lines about realms
  val midgardHelp = "Mimir:\nWell your choises are not limited at all lad. \nMidgard acts as a bridge between realms, so you must come here to travel between realms."
  val alfheimHelp = "Mimir:\nYou will need to get a Brightest Star of Alfheim from light elf queen to progress in your journey."
  val asgardHelp = "Mimir:\nYou got audience with all Father himself my friend.\nHe posesses one of the relics to open one of the seals at Temple of Aurgelmir."
  val muspelheimHelp = "Mimir:\nSeems like the realm of fire is a home to blaze heart containing a powerful relic. \nYou'll need this relic to get home"
  val svartalfheimHelp = "Mimir:\n Somewhere in the realm of dark elves awaits about to be corrupted Valkyrie of Healing Eir.\nYou'll need her blessing by cleansing her to obtain the relic from Odin"
  val niflfheimHelp = "Mimir:\nRealm of mist and ice containing the blizzard heart containing a powerful relic. \nYou'll need this relic to get home"
  val helheimHelp = "Mimir:\nThe stormy Valkyrie of reincarnation Kára awaits somewhere here. You'll need her blessing to obtain relic from Odin."
  val jotunheimHelp = "Mimir:\nThe main objective is here in Jotunheim.\nYou'll need to find your way to Temple of Aurgelmir by obtaining all four relics to open the seals"
  val vanaheimHelp = "Mimir:\nSurtr said that her beloved is located here. Complete Surtr's request after you proved yourself to him.\nDelivering the right message to Sinmara can get you something special."
  val commandsHelp = s"Mimir:\nAlright buckle up\nget       -> Pick up the inputted item that are in the area (Input command: pick (item name))\ndrop      -> Drop the inputted item from you inventory. Relics can't be dropped (Input command: drop (item name))\ninventory -> See what non-relic items you have (Input command: inventory)\nrest      -> Attempt to rest. It can have it's uses in certain place (Input command: rest)\nexamine   -> Examine selected item or relic in your posession (Input command: examine (item/relic name))\nrelics    -> See what relics you posess (Input command: relics)\nreach     -> Reach in inputted relic container (Input command: reach (container name))\ntalk      -> talk to NPC in the area (NOTICE: In case of NPC's the first letter of their name has to be higher case) (Input command: talk (NPC name))\nchallenge -> Challenge or help NPC in the area with a chance of obtaining relic (Input command: challenge (NPC name))\nreceive   -> receive the relic in posession of the NPC after defeating him/her (Input command: receive (relic name))\nobserve   -> Observe the inputted NPC. You will see the name, description, and item in posession of the NPC(Input command: obsrve (NPC name))\nuse       -> Use the inputted item in your posession(Input command: use (relic name))\nhelp/mimir -> Bring up help section (Input command: help)\ncombine   ->After you have collected the big three relics you can combine them at midgard (Input command: command)"
  
  //Mimir's lines after finding relics
  val bladeSurtrFound = "Mimir:\nBy the Allfather! It's The legendary blade of Surtr. No wonder the lavafall was so incredibly powerful.\nYou got a relic:\nblade of surtr"
  val cloakWinterFound = "Mimir:\nThe cloak of Winter! I thought it was destroyed after Odin slayed Ymir.\nIt contains essence of Blizzard Heart itself, and can protect you from the deadliest frosts.\nYou got a relic:\ncloak of winter"
  val shardBlizzardFound = "Mimir:\nA shard piece from Blizzard Heart itself!\nBe careful with that lad, even a scratch from that piece is enough to send a god to a eternal chilling afterlife. \nYou got a relic:\nshard of blizzard heart"
  
  // Narrator's description about areas
  val lakeDesc = "You arrive at one of the lakes in Aflheim. It's beautiful and clear as the path that light makes in total darkness.\nThe calmest place in any realm. The calmness feels your thoughts with positivity and you feel a little tired."
  val palaceDesc = "You arrive at the purest palace of all in Alfheim. The pure elegance and beautiful diamond like walls takes your breath away, as you stare at them.\nYou see a royal figure sitting on a throne. The elf queen Ljosalfar"
  val councilDesc = "The Council of Allfather himself. You see other gods and goddess' giving you an eyeful.\nYou feel incredible pride but also incredible pressure, as you feel an intense glare coming behind you. It's Allfather"
  val lavafallDesc = "You arrive at the edge of a cliff and next to you a violent fall of lava.\nThere is nowhere to go but back and nothing to get except some lavastones and the pure energy on the fall feels like melting you even from afar.\nYou sense an unusual aura from the lavafall, like... it's more than it looks."
  val volcanoDesc = "You arrive at the top of Muspelheim. You see a burnt like figure. Mimir recognises the person... It's Surtr"
  val darkMistDesc = "It feels like you're in your nightmares. No light source anywhere other than the star you're holding.\nYou turn and see an winged figure. It's the Valkyrie of Healing Eir"
  val roadDesc = "You see a long road ahead of you. So long you can't see if it even ends. Damned sould walk past you towards what you can only assume as infinite walk.\nMimir tells you to never in under any circumstances to follow the road. You see a realmtear not so far from your position... strange"
  val chamberDesc = "You enter an ancient runes. Inside it you find an abandoned chamber. It contains the Valkyrie of reincarnation Kara"
  val realmtearDesc= "A realmtear in the middle of nowhere?. You can feel a slight chill coming from it like... there is something in it."
  val coldMistDesc = "It's hard to see. You are in protection of the cloak but see many around you who weren't so lucky to endure the cold.\nAt far you see a light and you can feel even colder aura coming from it. Could it be...?"
  val zeroPointDesc = "You're at the coldest point of Niflheim. Without the cloak you wouldn't have a chance.\nThe light you saw before revealed to be the blizzard heart of niflheim. It can be reached into"

  //Relic descriptions
  val brightStarDesc = "A beautiful crystal containing bright light to slay through darkness"
  val crownOfSurtrDesc = "The Crown of the fire giant. It radiates powerful energy"
  val bladeOfSurtrDesc = "The most powerful weapon amongst giants. Prophecy says that this sword destroys Asgard. Maybe you have shaped the future"
  val blizzardHeartShardDesc = "Beautiful light crystal radiating the strongest frost among realms. Only you gauntlet protects and stores it for you"
  val cloakWinterDesc = "A lightweight cloak that's enough to protect you against frosty environment of Niflheim"
  val gauntletOfIceDesc = "Safe to touch gauntlet that gives the most incredible protection against frost"
  val runeOfRavensDesc = "Rune made by Allfather himself containing wisdosms of realms."
  val blessingOfKaraDesc = "Blessing bestowed upon you by the stormy Valkyrie."
  val blessingOfEirDesc = "Blessing bestowed upon you by the healer Valkyrie."
  


package o1.adventure
import o1.adventure.Lines
import o1.adventure.Characters.*

/** The class `Adventure` represents text adventure games. An adventure consists of a player and
  * a number of areas that make up the game world. It provides methods for playing the game one
  * turn at a time and for checking the state of the game.
  *
  * N.B. This version of the class has a lot of “hard-coded” information that pertains to a very
  * specific adventure game that involves a small trip through a twisted forest. All newly created
  * instances of class `Adventure` are identical to each other. To create other kinds of adventure
  * games, you will need to modify or replace the source code of this class. */
class Adventure:

  /** Descriptions and lines used by different NPC's */
  val lines = Lines()
  /** the name of the game */
  val title = "The mystical nordic adventure"

  /** Different Relics scattered across realms */
  val brightStar = Item("brightest star of alfheim",lines.brightStarDesc,false)
  val crownOfSurtr = Item("crown of surtr",lines.crownOfSurtrDesc,false)
  val theBladeOfSurtr = Item("blade of surtr", lines.bladeOfSurtrDesc,false)
  val blizzardHeartShard = Item("shard of blizzard heart",lines.blizzardHeartShardDesc,false)
  val cloakOfWinter = Item("cloak of winter",lines.cloakWinterDesc,false)
  val gauntletOfEternalIce = Item("gauntlet of eternal ice", lines.gauntletOfIceDesc,false)
  val runeOfRavens = Item("rune of ravens", lines.runeOfRavensDesc,false)
  val blessingOfKara = Item("blessing of the stormy valkyrie",lines.blessingOfKaraDesc,false)
  val blessingOfEir = Item("blessing of the healer valkyrie",lines.blessingOfEirDesc,false)


  /** Relic containers*/
  val blazeHeart = RelicContainer("lavafall",Option(theBladeOfSurtr), Option(crownOfSurtr))
  val blizzardHeart = RelicContainer("blizzard heart of niflheim",Option(blizzardHeartShard),Option(gauntletOfEternalIce))
  val chillingRealmTear = RelicContainer("realmtear",Option(cloakOfWinter), None)

  /** Realms */
  private val midgard     = Area("Midgard", lines.mimirMidgard,None,None)
  private val alfheim     = Area("Alfheim", lines.mimirAlfheim,None,None)
  private val asgard      = Area("Asgard", lines.mimirAsgard,None,None)
  private val muspelheim  = Area("Muspelheim", lines.mimirMuspelheim,None,None)
  private val svartalfheim= Area("Svartalfheim", lines.mimirSvartalfheim,None,None)
  private val helheim     = Area("Helheim", lines.mimirHelheim,None,None)
  private val niflheim    = Area("Niflheim", lines.mimirNiflheim, None,None)
  private val jotunheim   = Area("Jotunheim", lines.mimirJotunheim,None,None)
  private val vanaheim    = Area("Vanaheim", lines.mimirVanaheim,None,None)

  /** The character that the player controls in the game. */
  val player = Player(midgard)

  /** Other areas in realms */
  /** Alfheim */
  private val lake     = Area("Lake of Purity",lines.lakeDesc,None,None)
  private val palace   = Area("The Shining Palace of Alfheim",lines.palaceDesc,None,None)

  /** Asgard */
  private val council  = Area("The Council of Odin", lines.councilDesc,None,None)

  /** Muspelheim */
  private val volcano  = Area("Blazing Volcano of Muspelheim",lines.volcanoDesc,None,None)
  private val lavafall = Area("Eradicating fall of lava",lines.lavafallDesc,None,Option(blazeHeart))

  /** Svartalfheim */
  private val darkMist = Area("Blinding mist of Svartalfheim",lines.darkMistDesc,Option(brightStar),None)

  /** Helheim */
  private val road      = Area("The Never Ending Road of the Damned",lines.roadDesc,None,None)
  private val chamber   = Area("The soulless chamber of Hel",lines.chamberDesc,None,None)
  private val realmTear = Area("Odd chilling realmtear",lines.realmtearDesc,None,Option(chillingRealmTear))

  /** Niflheim */
  private val coldMist = Area("The glacial mist of Niflheim",lines.coldMistDesc,Option(cloakOfWinter),None)
  private val zero     = Area("The absolute deadly freezing point of Niflheim",lines.zeroPointDesc,Option(cloakOfWinter),Option(blizzardHeart))

  /** Jotunheim */
  private val templeOfYmirDoor = Area("Entrance of Temple of Aurgelmir",lines.mimirTempleEntrance,None,None)
  private  val templeOfYmir = Area("Temple of Aurgelmir","",Option(player.triageOfNorse),None)

  /** Vanaheim */
  //There is none

  /** Souvenirs to collect during your journey */
  vanaheim.addItem(Item("flowers", "Flowers from Vanaheim, they smell nice\nCan't be used for anything. Nice souvenir though",false))
  lavafall.addItem(Item("lavastone", "A cooled down lavastone from Muspelheim. It contains some power\nCan't be used for anything. Nice souvenir though",false))
  coldMist.addItem(Item("fossil", "A frozen fossil from Niflheim. You wonder what kind of creature it is.\nCan't be used for anything. Nice souvenir though",false))
  council.addItem(Item("raven feaher", "A feather from one of the ravens of Odin. It feels strangely magical.\nCan't be used for anything. Nice souvenir though",false))

  /** The areas that can be traveled from current location
    * Midgard acts as a center piece/bridge between realms */
  midgard     .setNeighbors(Vector("alfheim" -> alfheim, "asgard" -> asgard, "muspelheim" -> muspelheim, "svartalfheim" -> svartalfheim, "helheim" -> helheim, "niflheim" -> niflheim, "jotunheim" -> jotunheim, "vanaheim" -> vanaheim  ))
  alfheim     .setNeighbors(Vector("midgard" -> midgard, "palace" -> palace, "lake" -> lake))
  asgard      .setNeighbors(Vector("midgard" -> midgard, "council"-> council))
  muspelheim  .setNeighbors(Vector("midgard" -> midgard, "volcano" -> volcano, "lavafall" -> lavafall))
  svartalfheim.setNeighbors(Vector("midgard" -> midgard, "darkmist" -> darkMist))
  helheim     .setNeighbors(Vector("midgard" -> midgard, "road" -> road, "chamber" -> chamber))
  niflheim    .setNeighbors(Vector("midgard" -> midgard, "coldmist" -> coldMist))
  jotunheim   .setNeighbors(Vector("midgard" -> midgard, "temple entrance" -> templeOfYmirDoor))
  vanaheim    .setNeighbors(Vector("midgard" -> midgard))
  lake        .setNeighbors(Vector("alfheim" -> alfheim, "palace" -> palace))
  palace      .setNeighbors(Vector("alfheim" -> alfheim, "lake" -> lake))
  council     .setNeighbors(Vector("asgard"       -> asgard))
  volcano     .setNeighbors(Vector("muspelheim"   -> muspelheim))
  lavafall    .setNeighbors(Vector("muspelheim"   -> muspelheim))
  darkMist    .setNeighbors(Vector("svartalfheim" -> svartalfheim))
  road        .setNeighbors(Vector("helheim"      -> helheim, "realmtear" -> realmTear))
  chamber     .setNeighbors(Vector("helheim"      -> helheim))
  realmTear   .setNeighbors(Vector("road"         -> road))
  coldMist    .setNeighbors(Vector("niflheim"     -> niflheim, "zeropoint" -> zero))
  zero        .setNeighbors(Vector("coldmist"     -> coldMist))
  templeOfYmirDoor.setNeighbors(Vector("jotunheim"-> jotunheim, "temple" -> templeOfYmir))
  
  /** Different NPC's added all over realms */
  council.addNPC(NPC("Odin", "The all seeing, knowing and powerful Allfather Odin", Option(runeOfRavens), 3))
  palace.addNPC(NPC("Ljosalfar", "The brightest of elves and royal protector of Alfheim. The elf queen Ljosalfar", Option(brightStar),1))
  chamber.addNPC(NPC("Kara", "One of the Valkyries. Known as the stormy Valkyrie and has ablility to reincarnate the dead.", Option(blessingOfKara),0))
  darkMist.addNPC(NPC("Eir", "The kind Valkyrie of healing. While others were more focused on war and training Eir's hand was the most humble",Option(blessingOfEir),0))
  volcano.addNPC(NPC("Surtr", "The hot headed giant of fire. It is said that he brings Ragnarok when it starts so best to stay on his good side", Option(crownOfSurtr),2))
  vanaheim.addNPC(NPC("Sinmara", "A beautiful giantness familiar with ancient giant magic. Having left her battle days behind her after falling in love\nwith her partner on battlefield. She now makes her piece in Vanaheim",Option(gauntletOfEternalIce),2))

  /** The number of turns that have passed since the start of the game. */
  var turnCount = 0
  /** The maximum number of turns that this adventure game allows before time runs out. */
  val timeLimit = 100


  /** Determines if the journey is complete, that is, if the player has won. */
  def isComplete = this.player.location == this.templeOfYmir

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = lines.intro + lines.mimirIntro + "\n" + lines.start


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage =
    if this.isComplete then
      "Mimir:\nYou actually did it! I'm sure even all Father himself couldn't break this nut.\nConsider yourself in the category of gods and giants. Farewell my dear friend and live a long life! \n\nWell done you found your way home!.\nThank you for playing the \"The mystical nordic adventure\""
    else if this.turnCount == this.timeLimit then
      "Oh no! Time's up. The Ragnarök is upon us and nothing will stop it!.\nGame over!"
    else  // game over due to player quitting
      "Mimir:\nWhat a shame my friend. You had so much potential eh?"


  /** Plays a turn by executing the given in-game command, such as “go west”. Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(lines.afterInvalidCommand + s"\n(Invalid command: \"$command\")")

end Adventure


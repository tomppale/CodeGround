package o1.adventure

import scala.collection.mutable.Map
import scala.io.StdIn.*
import o1.adventure.RelicContainer
import o1.adventure.Characters.*
import o1.{Pic,show}
import java.net.URL
import javax.sound.sampled.*
import java.io.File
/** A `Player` object represents a player character controlled by the real-life user
  * of the program.
  *
  * A player object’s state is mutable: the player’s location and possessions can change,
  * for instance.
  *
  * @param startingArea  the player’s initial location */
class Player(startingArea: Area):

  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var carrying = Map[String,Item]()
  private var relics = Map[String, Item]()
  private val lines = Lines()
  private var blessingLevel = 0
  val triageOfNorse = Item("triage of norse","",true)
  private val kartta = Pic("map.png").scaleBy(0.53)

  def get(itemName: String): String =
    if currentLocation.contains(itemName) then
      val pickedUpItem = currentLocation.removeItem(itemName)
      pickedUpItem match
        case Some(arvo) =>
          carrying += arvo.name -> arvo
          s"You picked up the $itemName."
        case None => s"There is no $itemName here to pick up."
    else
      s"There is no $itemName here to pick up."

  /** Shows a map of this world to the player */
  def map() =
    show(kartta)
    ""

  def drop(itemName: String): String =
    if has(itemName) then
      if carrying.contains(itemName) then
        this.currentLocation.addItem(carrying(itemName))
        carrying.remove(itemName)
        s"You drop the $itemName."
      else "You can't drop relics"
    else
      "You don't have that!"

  def has(itemName: String): Boolean =
    carrying.contains(itemName) || relics.contains(itemName)

  def examine(itemName: String): String =
    var description = ""
    if has(itemName) then
      if carrying.contains(itemName) then
        description += carrying(itemName).description
        s"You look closely at the $itemName.\n$description"
      else
          description += relics(itemName).description
          s"You look closely at the $itemName.\n$description"
    else
      "If you want to examine something, you need to pick it up first."

  def inventory: String =
    if carrying.isEmpty then s"You are empty-handed.\nBlessing level: ${this.blessingLevel}"
    else s"You are carrying:\n${carrying.keys.mkString("\n")}\n\nBlessing level: ${this.blessingLevel}"

  def relicsInPosession: String =
    if relics.isEmpty then s"Mimir:\nEmpty ehh? Well lad, every collections starts from something aye?.\nBlessing level: ${this.blessingLevel}"
    else s"Mimir:\nLet's see your mythical collection:\n\n${relics.keys.mkString("\n")}\n\nBlessing level: ${this.blessingLevel}"

    /** Attemps to reach in relicContainer. If the player has the required item to
      * reach into the container. Assuming the container contains anything in the first place,
      * the player will get the contents in the relic inventory*/

  /** Player attempts to reach into relic container assuming there is a container in the
    * current location in the first place
    * Player gets it's contents if he/she posesses the required item to reach in */
  def reachIn(containerName: String) =
    val hasContInArea = this.currentLocation.allContainers.isDefined
    if hasContInArea && this.currentLocation.allContainers.get.theName == containerName then
      val container = this.currentLocation.allContainers.get
      val hasTheItem = container.mustHaveItem match
        case Some(toHave) => has(toHave.name)
        case None => true
      if hasTheItem then
        val canReach = container.mustHaveItem match
          case Some(theItem) => theItem.isInUse
          case None => true
        if canReach then
          container.removeItem() match
            case Some(relic) =>
              relics += relic.name -> relic
              relic.name match
                case "blade of surtr"        => lines.bladeSurtrFound
                case "cloak of winter"           => lines.cloakWinterFound
                case "shard of blizzard heart" => lines.shardBlizzardFound
            case None =>
              s"Mimir:\nAnother one bites the dust ehh?"
        else s"Mimir:\nStop my friend! You have to use the ${container.mustHaveItem.get.name} to reach in."
      else
        s"Mimir:\nYou cannot reach in that relic container without ${container.mustHaveItem.get.name}. \nA certain death awaits without the support from it."
    else
      s"Mimir:\nHmm, seems like there is not a relic container $containerName here lad."

  /** Receives relic from NPC after beating it. Used in command method in class "NPC" -> "givePosession" */
  def receiveRelic(item: Option[Item]) =
    val gottenItem = item.get
    relics += gottenItem.name -> gottenItem

  /** Brings up a menu containing helpful tips. */
  def help(): String =
    print("Mimir:\nOi lad. What can I help you with?\n")
    print("\n")
    print("1: Where can I go from here?\n")
    print("2: What items are there to collect here?\n")
    print("3: How do I get home?\n")
    print("4: What must I do in this realm?\n")
    print("5: Who can I interact with here?\n")
    print("6: Show me the commands\n")
    print("7: What are blessings?\n")
    print("8: Show me the map\n")
    val inputCheck = readLine("Enter a number of a question you want to know. Input 0 to back out: ").toIntOption
    print("\n")
    if inputCheck.isDefined then
      val input = inputCheck.get
      input match
        case 1 => "Mimir:\nSure lad. Places you can go from here are:\n" + this.currentLocation.allNeighbors.keys.mkString("\n") + "\nInput \"go\" + name of the realm to travel."
        case 2 => "Mimir: \nHmm, seems like there are following items around us:" + this.currentLocation.allItems.values.mkString("\n")
        case 3 => "Mimir: \nYour objective my friend is to collect all relics, that will break the seals keeping doors of Temple of Aurgelmir locked. \nThe relics are usually contained in relic containers which will always require some kind of magical item to reach into\nOne exception is Odin who holds one of the relics"
        case 4 =>
           this.currentLocation.name match
            case "Midgard"      => lines.midgardHelp
            case "Alfheim"      => lines.alfheimHelp
            case "Asgard"       => lines.asgardHelp
            case "Muspelheim"   => lines.muspelheimHelp
            case "Svartalfheim" => lines.svartalfheimHelp
            case "Helheim"      => lines.helheimHelp
            case "Niflheim"     => lines.niflfheimHelp
            case "Jotunheim"    => lines.jotunheimHelp
            case "Vanaheim"     => lines.vanaheimHelp
            case other          => "Go back to realm's entrance to get your objective."
        case 5 =>
          if this.currentLocation.areaContainsNPC then
            s"Mimir:\nNPC in the area: ${this.currentLocation.NPCInArea.get.theName}"
          else s"Mimir:\nLooks like no-one is in sight lad."
        case 6 => lines.commandsHelp
        case 7 => "Mimir:\nBlessings describe your ability to challenge characters. Some characters will require higher blessing levels to be challenged.\nTwo of the blessing can be obtained from Valkyries and the third... Well let's figure it out."
        case 8 => "Mimir:\nWise move, navigating is important" + this.map()
        case 0 => "Mimir:\nAlright lad, you know where to find me aye?"
    else "Mimir:\nSorry can't help ye"

  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven

  /** Returns the player’s current location. */
  def location = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player’s current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(place: String) =
    var itemNeededToGo = Vector[Item]()
    val destination = this.location.neighbor(place)
    destination match
      case Some(place) =>
        val hasItem = place.itemsToEnter match
          case Some(itemToEnter) =>
            itemNeededToGo = itemNeededToGo :+ itemToEnter
            has(itemToEnter.name)
          case None => true
        if hasItem then
          val canGo = itemNeededToGo.isEmpty || itemNeededToGo.head.isInUse
          if canGo then
            this.currentLocation = destination.get
            s"You traveled to $place.\n"
          else
            if destination.get.name == "Temple of Aurgelmir" then s"Mimir:\nAlmost there my friend. Just activate the triage to open the gate's seals and you are on your way."
            else s"Mimir:\nOi lad! Remember to use ${itemNeededToGo.head.name} so you can enter without harm."
        else
          if destination.get.name == "Temple of Aurgelmir" then "You'll need the special key by obtaining the 3 big relics \nthe \"rune of ravens\", \"shard of blizzard heart\" and \"blade of surtr\". Their combined power will create a key to travel between worlds"
          else s"Mimir:\nCareful lad. You'll need ${itemNeededToGo.head} to get to $place. It'll be dangerous otherwise."
      case None => s"There is no place called $place to go to."

  /** Player attempts to talk the NPC in the area assuming there is one */
  def talk(name: String) =
    var input = 4
    if this.currentLocation.areaContainsNPC then
      val npc = this.currentLocation.NPCInArea.get
      if npc.theName == name then
        while input != 0 do
          val inputCheck = readLine( npc.discussionStart(name) + "\n\n" + "What are you going to say?: ").toIntOption
          if inputCheck.isDefined then
            input = inputCheck.get
            print("\n")
            print(npc.qNdA(input, name))
            print("\n")
          else
           print("Invalid input\n\n")
        ""
      else s"There is no such person as  $name here."
    else s"There is no-one worth talking to."

  /** Player attempts to challenge the NPC in area for their posession.
    * Challenge is rejected if player doesn't have high enough blessing level */
  def challenge(name: String) =
    if this.currentLocation.areaContainsNPC then
      val npc = this.currentLocation.NPCInArea.get
      if npc.theName == name then
        if !npc.discuss.npcBeaten(name) then
          npc.challenged(name, blessingLevel)
        else s"You have alredy beaten $name"
      else s"There is no such person as $name to challenge here."
    else s"There is no-one to challenge."

  /** Command to use to actually receive the posession of the NPC in area after beating their challenge */
  def receive(itemName: String): String =
    if this.currentLocation.areaContainsNPC then
      val npc = this.currentLocation.NPCInArea.get
      if npc.discuss.npcBeaten(npc.theName) then
        if npc.itemInPosession.isDefined then
          val itemGot = npc.itemInPosession.get
          if itemGot.name == itemName then
            npc.givePosession(this)
          else s"${npc.theName} doesn't have ${itemName}\n(Use observe command to see what relic ${npc.theName} posesses)\n"
        else s"${npc.theName} doesn't have any relics\n"
      else s"You have to challenge and beat ${npc.theName} to receive the $itemName"
    else s"There is no-one to receive item or relic from"

  /** Allows player to examine the NPC in area and see their information. */
  def observe(name: String): String =
    if this.currentLocation.areaContainsNPC then
      val npc = this.currentLocation.NPCInArea.get
      val itemInPosess = npc.itemInPosession match
        case Some(theItem) => theItem.name
        case None => "None"
      s"Name: ${npc.theName}\nDescription: ${npc.desc}\nItem/Relic in posession: $itemInPosess\nBlessing required to challenge: ${npc.blessingLvl}"
    else s"There is no $name to observe here"

  /** Player attempts to use inputted item assuming the player has it in the first place */
  def use(itemName: String) =
    if relics.contains(itemName) then
      if !relics(itemName).isInUse then
        relics(itemName).useTheItem()
        itemName match
          case "brightest star of alfheim" => "You focus your energy to the crystal and little by little it starts glowing brigter and brighter.\nIt yearns to rid all the darkness."
          case "crown of surtr" => "You put on the crown of the fire giant.\nStrangely it doesn't melt your head as you were aftraid of. You feel strangely warm constantly but protection from heat at the same time"
          case "cloak of winter" => "You put on the cloak with essence of the Blizzard Heart. It gives you protection against colds of Niflheim."
          case "a shard of blizzard heart" => "You feel incredible power of blizzards emitting from the shard.\nGood thing your cloak protects you. Direct contact would be death for sure otherweise."
          case "gauntlet of eternal ice" => "You can feel a raw elemental power of frost giants themselves.\nThe gauntlet will protect you from the most violent of colds"
          case "rune of ravens" => "The rune of ravens contains wisdosms of odin. It will open one of the seals at the Temple of Ymir"
          case "blade of surtr" => "You feel power surging within you as you hold the blade. It wispers something... Ragnarok?"
          case "blessing of the healer valkyrie" =>
            this.relics.remove("blessing of the healer valkyrie")
            this.blessingLevel += 1
            s"You feel like reborn. You don't feel pain in anywhere, you just feel incredible,\nlike the blessing just took every pain away. Blessing of a healer Valkyrie does wonders\n\nYou received a blessing.\nCurrent level: $blessingLevel"
          case "blessing of the stormy valkyrie" =>
            this.relics.remove("blessing of the stormy valkyrie")
             this.blessingLevel += 1
             s"You feel the surge of bravery within you... Or is it something else? It's like... you can't die. \nThe blessing of stormy valkyrie brings you back from dead\n\nYou received a blessing.\nCurrent level: $blessingLevel"
          case "triage of norse" => "Mimir:\nIncredible my friend. The key to travel between worlds is active. You've been the most\ninteresting company I've had many winters and I thank you for that.\nNow time to get you home lad."
      else s"$itemName is already in use."
    else s"There's no $itemName to use."

  /** Causes the player to rest for a short while (this has no substantial effect in game terms).
    * Returns a description of what happened. */
  def rest() =
    if this.location.name == "Lake of Purity" then
      if this.location.countRestTimes == 0 then
        this.blessingLevel += 1
        this.currentLocation.addRest()
        s"You close your eyes and fall into a deep sleep. You dream of your fondest memories.\nSuddenly the fairies of Alfheim find their way to you. They sense your desire to get home and decide to bestow a blessing upon you.\nYou wake up\n\nYou received a blessing.\nCurrent level: $blessingLevel"
      else "You decide to rest again by the lake. You had a good rest"
    else "Mimir:\nI don't recommend resting here lad. \nAlmost every realm contains danger.. Although maybe the lake of alfheim would be peaceful"

  /** Player attempts to combine the three big relics. Fails if player doesn't have them all or is not in midgard */
  def combine() =
    if this.relics.contains("rune of ravens") && this.relics.contains("shard of blizzard heart") && this.relics.contains("blade of surtr") && this.currentLocation.name == "Midgard" then
      relics += triageOfNorse.name -> triageOfNorse
      relics.remove("rune of ravens")
      relics.remove("shard of blizzard heart")
      relics.remove("blade of surtr")
      "You combine the rune of ravens, shard of blizzard heart and blade of surtr with each other and something magical happens.\n the Mimir:\nSo it's not a myth after all. Time to go home lad.\n\nYou received a relic:\ntriage of norse"
    else "Mimir:\nYou're not ready yet my friend. Make sure you have \"rune of ravens\", \"shard of blizzard heart\", \"blade of surtr\" and you're in midgard"

  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() =
    this.quitCommandGiven = true
    ""


  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player


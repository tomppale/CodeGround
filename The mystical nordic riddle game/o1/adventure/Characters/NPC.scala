package o1.adventure.Characters
import o1.adventure.{Area, Item, Player, Adventure}

class NPC(name: String, description: String, posession: Option[Item], blessingLevel: Int):

  val discuss = Discussion()
  private var posessedItem: Option[Item] = posession
  val riddleSolved = false
  
  /** Returns this NPC's name */
  def theName = this.name
  
  /** Returns the relic this NPC posesses wrapped in Option */
  def itemInPosession = posessedItem
  
  /** Returns a description of this NPS */
  def desc = this.description
  
  /** Returns the blessing level required to challenge this NPC */
  def blessingLvl = blessingLevel
  
  /** Starts a discussion when player request it by "talk" command */
  def discussionStart(name: String) =
    name match
      case "Odin" => discuss.odinOpening + discuss.odinQuestions
      case "Ljosalfar" => discuss.ljosalfarOpening + discuss.ljosalfarQuestions
      case "Eir" => discuss.eirOpening + discuss.eirQuestions
      case "Kara" =>discuss.karaOpening + discuss.karaQuestions
      case "Surtr" => discuss.surtrOpening + discuss.surtrQuestions
      case "Sinmara" => discuss.sinmaraOpening + discuss.sinmaraQuestions
      case other  =>  s"Mimir:\nNo $name in sight lad?"
  
  /** Makes NPC to answer the player's question */
  def qNdA(input: Int, name: String) =
    name match
      case "Odin" =>
        input match
          case 1 => discuss.odinAnswers(1)
          case 2 => discuss.odinAnswers(2)
          case 3 => discuss.odinAnswers(3)
          case other => "Odin:\nHmph\n"
      case "Ljosalfar" =>
        input match
          case 1 => discuss.ljosalfarAnswers(1)
          case 2 => discuss.ljosalfarAnswers(2)
          case 3 => discuss.ljosalfarAnswers(3)
          case other => "Ljosalfar:\nSee you\n!"
      case "Eir" =>
        input match
          case 1 => discuss.eirAnswers(1)
          case 2 => discuss.eirAnswers(2)
          case 3 => discuss.eirAnswers(3)
          case other => "Eir:\nUmm okay.. Ahem! I mean farewell!\n"
      case "Kara" =>
        input match
          case 1 => discuss.karaAnswers(1)
          case 2 => discuss.karaAnswers(2)
          case 3 => discuss.karaAnswers(3)
          case other => "Kara:\nYou done wasting my time?\n"
      case "Surtr" =>
        input match
          case 1 => discuss.surtrAnswers(1)
          case 2 => discuss.surtrAnswers(2)
          case 3 => discuss.surtrAnswers(3)
          case other => "Surtr:\n...\n"
      case "Sinmara" =>
        input match
          case 1 => discuss.sinmaraAnswers(1)
          case 2 => discuss.sinmaraAnswers(2)
          case other => "Sinmara:\nGoodbye then...\n"
  
  /** Starts the challenge when player challenges this NPC */
  def challenged(name: String, blessingRequired: Int) =
    name match
      case "Odin" => if blessingRequired == blessingLevel then discuss.riddleTaskOdin else s"Blessing level not high enough\nRequired $blessingLevel"
      case "Ljosalfar" => if blessingRequired >= blessingLevel then discuss.riddleTaskLjosalfar else s"Blessing level not high enough\nRequired $blessingLevel"
      case "Eir" => discuss.riddleTaskEir
      case "Kara" => discuss.riddleTaskKara
      case "Surtr" => if blessingRequired >= blessingLevel then discuss.riddleTaskSurtr else s"Blessing level not high enough\nRequired $blessingLevel"
      case "Sinmara" => if blessingRequired >= blessingLevel then discuss.taskSinmara else s"Blessing level not high enough\nRequired $blessingLevel"



  /** Makes NPC give their posession after being beating and player's "receive" command */
  def givePosession(player: Player) =
    val givenRelic = posessedItem.get.name
    player.receiveRelic(this.posessedItem)
    this.posessedItem = None
    name match
      case "Odin" => "Odin frowns and tossess the rune in front of you" + s"\n\nYou received $givenRelic."
      case "Ljosalfar" => "Ljosalfar presents the brightest star of Alfheim to you. Your legs shake from it's beauty" + s"\n\nYou received $givenRelic."
      case "Eir" => "Eir spreads her wings and her helm starts to glow. From helm a white light appears and makes it's way to other relics\n(to receive blessing, you have to use it from your relics)"+ s"\n\nYou received a relic:\n$givenRelic"
      case "Kara" => "Kara spreads her wing and helm starts to glow. From helm a red light appears and makes it's way to other relics\n(to receive blessing, you have to use it from your relics)"+ s"\n\nYou received a relic:\n$givenRelic"
      case "Surtr" => "Surtr brings his hands slowly above his head and raises his crown high. He gracefully gives it to you and despite your fear, it doesn't burn" + s"\n\nYou received a relic:\n$givenRelic"
      case "Sinmara" => "Sinmara hands you the legendary gauntlet of eternal ice. You take fright as she gives it to you, but despite its look, it's not cold.\nThe gauntlet will protect you against even fiercest frostbites"+ s"\n\nYou received a relic:\n$givenRelic"


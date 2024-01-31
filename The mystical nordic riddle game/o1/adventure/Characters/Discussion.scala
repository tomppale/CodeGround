package o1.adventure.Characters
import scala.io.StdIn.*
import o1.adventure.{Area, Item, Player, Adventure}

class Discussion:

  def npcBeaten(name: String) =
    name match
      case "Odin" => odinRiddlesCompleted
      case "Ljosalfar" => ljosalfarRiddleCompleted
      case "Eir" => eirRiddleCompleted
      case "Kara" => karaRiddleCompleted
      case "Surtr" => surtrRiddlesComleted
      case "Sinmara" => sinmaraTaskCompleted
      case other  => false

  private var odinRiddlesCompleted = false
  private var ljosalfarRiddleCompleted = false
  private var eirRiddleCompleted = false
  private var karaRiddleCompleted = false
  private var surtrRiddlesComleted = false
  private var sinmaraTaskCompleted = false

  /** Odin */
  val odinOpening = "You see an elderly man. At first glance fragile looking, but you can feel a powerful aura emmiting of him.\nYou know that showing respect is best course of action\n\nOdin:\nWhat are you looking at pipsqueak?\n\n"
  val odinQuestions = Map[Int,String](1 -> "What happened to your eye?", 2 -> "What is Ragnarök?", 3 -> "Can you help me to get home?", 0 -> "Back").mkString("\n")
  val odinAnswers = Map[Int,String](1 -> "To be the greatest leader you have to make sacrifices for greater good.\nSo I traded my eye for a infinity wisdom?", 2 -> "Have ye been livin under a rock or something? The end of the end of the world of gods and men.\nFor Ragnarök I have gathered the greatest warriors that have fallen in battle. They await my command in Valhalla", 3 -> "Hahah, you think I would let you have the rune of ravens? \nHow amusing, but tell you what, if you can answer the three mysteries of Asgard, then maybe then I would be willing to give you the rune.\nWhat says you?\n\n(To challenge Odin, your blessing level has to be 3)")
  val odinRiddles = Map[Int, String](1 -> "Death itself I swallow, to spring forth life tomorrow", 2 -> "Without me or within me death is sure, yet within you I am life most pure", 3 -> "I can break, I can be clogged, I can be attacked, I can be given, \nI can be kept, I can be crushed, yet I can be whole at the same time.")
  val odinAfterWin = "Odin:\nHmph, seems like you are smarter than you look. Oh well here, have the damn rune and get out of my sight"
  val odinAfterFail = "Hahahaa, is there anything going on in that little stump of yers?"

  def riddleTaskOdin =
    val acceptChallenge = readLine("Challenge Odin? (yes/no): ").toLowerCase
    print("\n")
    if acceptChallenge == "yes" then
      val answer1 = readLine(odinRiddles(1) + "\n" + "Your answer: ").toLowerCase
      print("\n")
      if answer1 == "earth" then
        val answer2 = readLine(odinRiddles(2) + "\n" + "Your answer: ").toLowerCase
        print("\n")
        if answer2 == "water" then
          val answer3 = readLine(odinRiddles(3) + "\n" + "Your answer: ").toLowerCase
          print("\n")
          if answer3 == "heart" then
            this.odinRiddlesCompleted = true
            odinAfterWin
          else odinAfterFail
        else odinAfterFail
      else odinAfterFail
    else "Hah, should've know you ain't got the stones"

  /** Ljosalfar */
  val ljosalfarOpening = "Her presence feels like it's cleaning your soul.\nBefore you realise you're taking a knee in front of her. She leans forward.\n\nLjosalfar:\nHmm well this is interesting. You are not from here. How may I help you?\n\n"
  val ljosalfarQuestions = Map[Int,String](1 -> "Elves!?.. I have never seen one!", 2 -> "Are there dark elves?", 3 -> "Can light of a queen guide me home?", 0 -> "Back").mkString("\n")
  val ljosalfarAnswers = Map[Int,String](1 -> "Ljosalfar:\nWell yes it is quite common here in Alfheim.\nEvery light elf has gotten the blessing from life itself.\nEven those resting by the brightest lake of Alfheim will receive blessing from the fairies", 2 -> "Ljosalfar:\nThe conterbalance to our light is their darkness\n Light elves and dark elves have been in conflict as long as me and dark elf king Dökkafar have lived", 3 -> "Ljosalfar:\nI can sense your desire to get home.\nA fragment from Firstlight the brightest star of Alfheim could light your way through darkness, but only those who are worthy can contain it's power.\n Show me your wisdom and I will help you\n\n(To challenge Ljosalfar your blessing level has to be 1 or above)")
  val ljosalfarRiddle = "I’m bright but I’m not clever, I burn but I’m not a bonfire,\nI sound like I’m a celebrity but I’m not famous, I twinkle but I’m not an eye, I can be seen at night but I’m not the moon"
  val ljosalfarAfterWin = "Ljosalfar:\nSuch wisdom. You are truly worthy of our brightest star.\nDon't worry we have more where that came from."
  val ljosalfarAfterFail = "Ljosalfar:\nDon't worry, even the smartest man among realms would have problems with that.\n\nMimir:\nHey!!"

  def riddleTaskLjosalfar =
     val acceptChallenge = readLine("Challenge Ljosalfar? (yes/no): ").toLowerCase
     print("\n")
      if acceptChallenge == "yes" then
        val answer = readLine(ljosalfarRiddle + "\n" + "Your answer: ").toLowerCase
        print("\n")
        if answer == "star" then
          this.ljosalfarRiddleCompleted = true
          ljosalfarAfterWin
        else ljosalfarAfterFail
      else "Ljosalfar:\nThat's okay come see me when you're ready."

  /** Eir */
  val eirOpening = "You glance down a shadowy figure. \nAs you bring your light closer, out of darkness a beautiful Valkyrie emerges. She raises her head\n\nEir: Greetings warrior what brings you to shadows of Svartalfheim?\n"
  val eirQuestions = Map[Int,String](1 -> "What are you doing here?", 2 -> "What are Valkyries?", 3 -> "Don't really wanna end up in Valhalla or Hel. Can you help me to get home?", 0 -> "Back").mkString("\n")
  val eirAnswers = Map[Int,String](1 -> "Eir:\nAllfather has assigned me to heal the wounded Valkyries in Svartalfheim.\nAs Ragnarök approaches because of fear, dark elves are out of control. We Valkyries were sent here to handle the situation.", 2 -> "Eir:\nValkyries are \"choosers of the slain\".\nWe quide the souls of those, who have died in a battle to the hall of Allfather Valhalla to fight with Odin when Ragnarök begins.", 3 -> "Eir:\nUnfortunately I have no power or resources to help you.\nThe shadows of Svartalfheim is corrupting me but I've found a cleansing rune which could help me.\nUnfortunately it's foreign language which I can't read. If you help me by transfering this rune I will give you my blessing.\nMimir:\nChallenge that a Valkyrie can't solve? Well call me surprised")
  val eirRiddle = "You read the rune's engravings:\nVoin rakentaa linnan sekunnissa. Voin löytää miljoona timanttia sekunnissa.\nVoin halkaista timantin sekunnissa. Mikä olen? Jos sinulla ei ole minua, sinulla on useimmiten tylsää."
  val eirAfterWin = "Eir:\nIncredible my friend. How did you...? Well nevermind that.\nI will be in your debt. Oh and the blessing a promised."
  val eirAfterFail = "Eir:\nShame my friend I thought I'd finally... Well *sniff* none of that matters now"

   def riddleTaskEir =
     val acceptChallenge = readLine("Help Eir by solving the engravings? (yes/no): ").toLowerCase
     print("\n")
      if acceptChallenge == "yes" then
        val answer = readLine(eirRiddle + "\n" + "Your answer: ").toLowerCase
         print("\n")
        if answer == "mielikuvitus" then
          this.eirRiddleCompleted = true
          eirAfterWin
        else eirAfterFail
      else "Eir:\nPlease my friend help me. I don't know how much can I take it anymore"

   /** Kara */
   val karaOpening = "Out of nowhere a sharp wing meets your neck. Lucky for you it doesn't go through but you can feel a pressure on your neck, \nknowing that one wrong move could be your last.\n\nKara:\nHow did a damned soul... Oh wait a second you're not dead at all. Who are you? What do you want?\n"
   val karaQuestions = Map[Int,String](1 -> "What's up with those wings", 2 -> "What is a Valkyrie doing in Hel", 3 -> "Well as you can see I'm not dead or from this world so can you get me home?", 0 -> "Back").mkString("\n")
   val karaAnswers = Map[Int,String](1 -> "Kara:\nEvery Valkyrie has their unique sets of wings as they define us and as you can see they are not somtehing to meddle with.\nYou're lucky you know. If Brynhild or Sigrun would've been in my shoes, your head would roll with the damned.", 2 -> "Kara:\nStrange isn't it. Bloody Ragnarök is starting to affect the realms as it gets closer. The already dead are\ntrying to get out of their punishment and I'm here to put them in their place.", 3 -> "Kara:\nValkyries have no control over the Temple of Ymir, so can't help...\nAah what the hell your slobbering is so pathetic! Okay if you beat my challenge, you will receive my blessing")
   val karaRiddles = Map[Int, String](1 -> "It brings back the lost as though never gone, shines laughter and tears with light long since shone, a moment to make, a lifetime to shed,\nvalued then but lost when your dead. What Is It?", 2 -> "Poor people have it, Rich people need it, if you eat it you die, what is it?")
   val karaAfterWin = "Kara:\nYou have my respect my friend. Not a single human have ever known these answers, except that bloody Mimir. Now hold still"
   val karaAfterFail = "Kara:\nHmph all bark but no show it seems"

   def riddleTaskKara =
     val acceptChallenge = readLine("Challenge Kara? (yes/no): ").toLowerCase
     print("\n")
      if acceptChallenge == "yes" then
        val answer1 = readLine(karaRiddles(1) + "\n" + "Your answer: ").toLowerCase
        print("\n")
        if answer1 == "memory" then
          val answer2 = readLine(karaRiddles(2) + "\n" + "Your answer: ").toLowerCase
          print("\n")
          if answer2 == "nothing" then
            karaRiddleCompleted = true
            karaAfterWin
          else karaAfterFail
        else karaAfterFail
      else "Kara:\nA decision I agree with"

   /** Surtr */
   val surtrOpening = "You see Surtr at the edge of volcano, looking over the realm of fire. You decide to join beside him sharing a quiet moment until.\nSuddenly he takes a deep breath\n\nSurtr:\nBeautiful isn't it. The warmth, nothing but ashes and fire, the purest form of life. Shame it's gonna be behind us soon.\n"
   val surtrQuestions =  Map[Int,String](1 -> "You aren't quite what I thought", 2 -> "What is up between you and Ragnarök?", 3 -> "Rather not be here when Ragnarök starts. Can you help me to get home?", 4 -> "Back").mkString("\n")
   val surtrAnswers = Map[Int,String](1 -> "Surtr:\nThen what did you think about me. \"Surtr the fierce fire giant who knows nothing but carnage\" but everything changed\nwhen I met Sinmara. After Odin learned about the Ragnarök's prophecy, he made sure I could never leave this realm. \nI hear that Sinmara stays in Vanaheim", 2 -> "The prophecy says that I bring Ragnarök, but the truth is, I will be the Ragnarök\nEven Odin doesn't know about this. Seems that Allfather ain't that all knowing.", 3 -> "Well my friend, I can give you my crown if that helps. It does hold some sentimental value, so if you can prove to be wise and trustworthy, it's yours.")
   val surtrRiddles = Map[Int,String](1 -> "Feed me not, we both are doomed. Overfeed me, all consumed.", 2 -> "Greatest man nor tallest tree. Begins as any more than me", 3 -> "As we are, we two, we three. As I alone will never be")
   val surtrAfterWin = "Surtr:\nWise as Allfather himself. Here something to remember me before... Well upcoming\nHey... if you see Sinmara, please tell her my message \"er love pu\"."
   val surtrAfterFail = "Surtr:\nNot impressive my friend. Come back when you mind is more open"

   def riddleTaskSurtr =
    val acceptChallenge = readLine("Prove yourself to Surtr? (yes/no): ").toLowerCase
    print("\n")
    if acceptChallenge == "yes" then
      val answer1 = readLine(surtrRiddles(1) + "\n" + "Your answer: ").toLowerCase
      print("\n")
      if answer1 == "fire" then
        val answer2 = readLine(surtrRiddles(2) + "\n" + "Your answer: ").toLowerCase
        print("\n")
        if answer2 == "seed" then
          val answer3 = readLine(surtrRiddles(3) + "\n" + "Your answer: ").toLowerCase
          print("\n")
          if answer3 == "family" then
            this.surtrRiddlesComleted = true
            surtrAfterWin
          else surtrAfterFail
        else surtrAfterFail
      else surtrAfterFail
    else "Surtr:\nBeing wise requires the courage to be unwise"

   /** Sinmara */
   val sinmaraOpening = "You approach the beautiful giantness. As you close on her she raises an eyebrow but keeps her beautiful smile.\n\nSinmara:\nHello there! May I assist you somehow?\n"
   val sinmaraQuestions = Map[Int,String](1 -> "It's so much different in here compared to other realms", 2 -> "I have message for you from Surtr", 0 -> "Back").mkString("\n")
   val sinmaraAnswers = Map[Int, String](1 -> "Sinmara:\nIsn't it. Vanaheim has always been so peaceful and very prosperous. Unlike gods the Vanir know humility and some manners\nI would've hoped that my beloved would've made here with me. I wonder how he is doing?", 2 -> "Sinmara\nA message from Surtr? Do not challenge my faith. How am I supposed to know that you're not just one Odin's goons trying to get something for me?")
   val sinmaraAfterWin = "You feel the crown you got from Surtr vibrating and getting hotter. Suddenly a trail of smoke comes out of the crown and forms a figure of \na person. It's Surtr. The crown starts letting out voice of Surtr speaking to Sinmara. You decide to leave the scene to give them some privacy. After a while you see \nthe smoke disappearing and make your way back. You see Sinmara wiping her tears.\nSinmara:\nI apologize for doubting you. As thanks please take this\nYou look down and Mimir recognizes it to be gauntlet of eternal ice"
   val sinmaraAfterFail ="Sinmara:\nLike my beloved would ever say that. Please leave"
   
   def taskSinmara =
     val acceptChallenge = readLine("Prove Sinmara's doubts wrong? (yes/no): ").toLowerCase
      print("\n")
      if acceptChallenge == "yes" then
        val answer = readLine("What was the message Surtr sent you to give?" + "\n" + "Your answer: ").toLowerCase
        print("\n")
        if answer == "er love pu" then
          sinmaraTaskCompleted = true
          sinmaraAfterWin
        else sinmaraAfterFail
      else "Sinmara:\nGood go it will be easier for both of us"
package o1.adventure

/** A container containing powerful relics that can aid the player during his/her quest */
/** An item might be needed to get the content of the container */

class RelicContainer(name: String, contains: Option[Item], mustHave: Option[Item]):
  
  private var containingRelic = contains

  /** Required item to reach into this container */
  val mustHaveItem = mustHave

  /** Returns the name of this container */
  def theName = this.name

  /** Gives it's contents to the player if player reaches in and has required relic */
  def removeItem(): Option[Item] = 
    val removedItem = containingRelic
    containingRelic = None
    removedItem
  
  
  
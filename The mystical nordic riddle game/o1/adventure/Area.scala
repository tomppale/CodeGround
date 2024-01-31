package o1.adventure

import scala.collection.mutable.Map
import o1.adventure.RelicContainer
import o1.adventure.Characters.*
/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an “area” can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String, mustHave: Option[Item], relicContainer: Option[RelicContainer]):
  
  private val relicContInArea = relicContainer
  private val neighbors = Map[String, Area]()
  private var items = Map[String, Item]()
  val itemsToEnter = mustHave
  var NPCInArea: Option[NPC] = None
  private var restedHere = 0
  private var hasYmirTempleDoor = false
  
  /** Counts how many times player player has rested in the current area. Applies only to Alheim's lake */
  def countRestTimes = restedHere
  
  def addRest() = restedHere += 1
  
  /** Adds NPC to a given area */
  def addNPC(NPC: NPC) =
    this.NPCInArea = Some(NPC)
    
  /** Determines if player's current location contains any NPC */
  def areaContainsNPC =
    NPCInArea.isDefined
  
  /** Checks if the current area contains a relic container */
  def allContainers = relicContInArea
  
  def allNeighbors = this.neighbors

  def allItems = this.items
  
  /** Checks if area requires a relic to enter */
  def needsItemsToEnter =
     itemsToEnter match
      case Some(theItem) => theItem
      case None => None
  
  def addItem(item: Item): Unit =
    this.items += item.name -> item

  def contains(itemName: String): Boolean =
    items.contains(itemName)

  def removeItem(itemName: String): Option[Item] =
    var removedItem: Option[Item] = None
    if items.isEmpty then
      removedItem = None
    else
      removedItem = items.get(itemName)
      items.remove(itemName)
    removedItem

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)

  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction–area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. If there are no
    * items present, the return value has the form "DESCRIPTION\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". If there are one or more items present, the return
    * value has the form "DESCRIPTION\nYou see here: ITEMS SEPARATED BY SPACES\n\nExits available:
    * DIRECTIONS SEPARATED BY SPACES". The items and directions are listed in an arbitrary order. */
  def fullDescription =
    val exitList = "\n\nAreas available: " + this.neighbors.keys.mkString(", ")
    val allItems = "\nYou see here: " + items.values.mkString(", ")
    if this.items.isEmpty then
      this.description + exitList
    else
      this.description + allItems + exitList

  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name

end Area


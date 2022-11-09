package me.iron.mod.MYMODHERE.element;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.schema.game.common.data.element.ElementCategory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

import api.config.BlockConfig;
import me.iron.mod.MYMODHERE.element.block.Block;
import me.iron.mod.MYMODHERE.element.block.systems.chambers.ChamberBlock;
import me.iron.mod.MYMODHERE.element.items.Item;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class ElementManager {

	public enum FactoryType {NONE, CAPSULE_REFINERY, MICRO_ASSEMBLER, BASIC_FACTORY, STANDARD_FACTORY, ADVANCED_FACTORY}

	private static final ArrayList<Block> blockList = new ArrayList<>();
	private static final ArrayList<ChamberBlock> chamberList = new ArrayList<>();
	private static final ArrayList<Item> itemList = new ArrayList<>();

	public static void initialize() {
		for(Block blockElement : blockList) blockElement.initialize();
		for(ChamberBlock chamber : chamberList) chamber.initialize();
		for(Item item : itemList) item.initialize();
	}

	public static ArrayList<Block> getAllBlocks() {
		return blockList;
	}

	public static ArrayList<ChamberBlock> getChamberList() {
		return chamberList;
	}

	public static ArrayList<Item> getAllItems() {
		return itemList;
	}

	public static Block getBlock(short id) {
		for(Block blockElement : getAllBlocks()) if(blockElement.getBlockInfo().getId() == id) return blockElement;
		return null;
	}

	public static Block getBlock(String blockName) {
		for(Block block : getAllBlocks()) {
			if(block.getBlockInfo().getName().equalsIgnoreCase(blockName)) return block;
		}
		return null;
	}

	public static ChamberBlock getChamber(short id) {
		for(ChamberBlock chamber : getChamberList()) {
			if(chamber.getBlockInfo().getId() == id) return chamber;
		}
		return null;
	}

	public static ChamberBlock getChamber(String name) {
		for(ChamberBlock chamber : getChamberList()) {
			if(chamber.getBlockInfo().getName().equalsIgnoreCase(name)) return chamber;
		}
		return null;
	}

	public static Item getItem(short itemId) {
		for(Item item : getAllItems()) {
			if(item.getItemInfo().getId() == itemId) return item;
		}
		return null;
	}

	public static Item getItem(String itemName) {
		for(Item item : getAllItems()) {
			if(item.getItemInfo().getName().equalsIgnoreCase(itemName)) return item;
		}
		return null;
	}

	public static void addBlock(Block block) {
		blockList.add(block);
	}

	public static void addChamber(ChamberBlock chamber) {
		chamberList.add(chamber);
	}

	public static void addItem(Item item) {
		itemList.add(item);
	}

	public static ElementCategory getCategory(String path) {
		String[] split = path.split("\\.");
		ElementCategory category = ElementKeyMap.getCategoryHirarchy();
		for(String s : split) {
			boolean createNew = false;
			if(category.hasChildren()) {
				for(ElementCategory child : category.getChildren()) {
					if(child.getCategory().equalsIgnoreCase(s)) {
						category = child;
						break;
					}
					createNew = true;
				}
			} else createNew = true;
			if(createNew) category = BlockConfig.newElementCategory(category, StringUtils.capitalize(s));
		}
		return category;
	}

	public static ElementInformation getInfo(String name) {
		Block block = getBlock(name);
		if(block != null) return block.getBlockInfo();
		else {
			Item item = getItem(name);
			if(item != null) return item.getItemInfo();
			else {
				for(ElementInformation elementInfo : ElementKeyMap.getInfoArray()) {
					if(elementInfo.getName().equalsIgnoreCase(name)) return elementInfo;
				}
			}
			return null;
		}
	}
}

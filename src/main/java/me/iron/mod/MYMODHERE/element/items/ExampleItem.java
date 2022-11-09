package me.iron.mod.MYMODHERE.element.items;

import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.schine.graphicsengine.core.GraphicsContext;

import api.config.BlockConfig;
import me.iron.mod.MYMODHERE.element.ElementManager;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/23/2022]
 */
public class ExampleItem extends Item {

	public ExampleItem() {
		super("Example Item", ElementManager.getCategory("Items"));
	}

	@Override
	public void initialize() {
		if(GraphicsContext.initialized) {
			itemInfo.setTextureId(ElementKeyMap.getInfo(341).getTextureIds());
			itemInfo.setBuildIconNum(ElementKeyMap.getInfo(341).getBuildIconNum());
		}
		itemInfo.setDescription("A cool item that does nothing.");
		itemInfo.setInRecipe(true);
		itemInfo.setShoppable(true);
		itemInfo.setPlacable(false);
		itemInfo.setPhysical(false);
		BlockConfig.add(itemInfo);
	}
}

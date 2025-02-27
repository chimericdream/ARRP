package net.devtech.arrp.api;

import java.util.List;

import net.devtech.arrp.util.IrremovableList;

import net.minecraft.resource.ResourcePack;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface RRPCallback {
	/**
	 * Register your resource pack at a higher priority than minecraft and mod resources
	 */
	Event<RRPCallback> BEFORE_VANILLA = EventFactory.createArrayBacked(RRPCallback.class, r -> rs -> {
		IrremovableList<ResourcePack> packs = new IrremovableList<>(rs, $ -> {});
		for (RRPCallback callback : r) {
			callback.insert(packs);
		}
	});

	/**
	 * Register your resource pack at a lower priority than minecraft and mod resources
	 */
	Event<RRPCallback> AFTER_VANILLA = EventFactory.createArrayBacked(RRPCallback.class, r -> rs -> {
		IrremovableList<ResourcePack> packs = new IrremovableList<>(rs, $ -> {});
		for (RRPCallback callback : r) {
			callback.insert(packs);
		}
	});

	/**
	 * @deprecated unintuitive name
	 * @see #BEFORE_VANILLA
	 */
	@Deprecated
	Event<RRPCallback> EVENT = BEFORE_VANILLA;

	/**
	 * you can only add resource packs to this list, you may not remove them
	 */
	void insert(List<ResourcePack> resources);
}

package com.github.startsmercury.simplynoshading.client.gui.options;

import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_ALL_NAME;
import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_ALL_TOOLTIP;
import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_ANY_TOOLTIP;
import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_BLOCKS_NAME;
import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_CLOUDS_NAME;
import static com.github.startsmercury.simplynoshading.client.SimplyNoShadingOption.SHADE_FLUIDS_NAME;
import static com.github.startsmercury.simplynoshading.mixin.sodium.SodiumGameOptionPagesAccessor.getVanillaOpts;
import static me.jellysquid.mods.sodium.client.gui.options.OptionFlag.REQUIRES_RENDERER_RELOAD;
import static me.jellysquid.mods.sodium.client.gui.options.OptionImpact.LOW;

import java.util.LinkedList;
import java.util.List;

import org.jetbrains.annotations.ApiStatus.Internal;

import com.github.startsmercury.simplynoshading.client.SimplyNoShadingOptions;
import com.google.common.collect.ImmutableList;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI;
import me.jellysquid.mods.sodium.client.gui.options.Option;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * Contains page(s) shown in {@link SodiumOptionsGUI} which also contains pages
 * from {@link SodiumGameOptionPages}.
 */
public class SimplyNoShadingGameOptionPages {
	/**
	 * {@link #shading() Shading page} name.
	 */
	@Internal
	public static final TranslatableComponent SHADING_NAME;

	/**
	 * {@link #SHADING_NAME Shading page name} key.
	 */
	@Internal
	public static final String SHADING_NAME_KEY;

	static {
		SHADING_NAME_KEY = "simply-no-shading.options.pages.shading";
		SHADING_NAME = new TranslatableComponent(SHADING_NAME_KEY);
	}

	/**
	 * Creates the shading page, the same way other pages in
	 * {@link SodiumGameOptionPages} are created.
	 *
	 * @return a new shading page
	 */
	public static final OptionPage shading() {
		final List<OptionGroup> groups;
		final Option<Boolean> shadeAll;
		final OptionGroup shadeAllGroup;
		final OptionGroup shadeAnyGroup;
		final Option<Boolean> shadeBlocks;
		final Option<Boolean> shadeClouds;
		final Option<Boolean> shadeFluids;

		groups = new LinkedList<>();
		shadeAll = OptionImpl.createBuilder(boolean.class, getVanillaOpts()).setName(SHADE_ALL_NAME)
				.setTooltip(SHADE_ALL_TOOLTIP).setControl(TickBoxControl::new)
				.setBinding((options, shading) -> ((SimplyNoShadingOptions) options).setShadeAll(shading),
						options -> ((SimplyNoShadingOptions) options).isShadeAll())
				.setImpact(LOW).setFlags(REQUIRES_RENDERER_RELOAD).build();
		shadeBlocks = OptionImpl.createBuilder(boolean.class, getVanillaOpts()).setName(SHADE_BLOCKS_NAME)
				.setTooltip(SHADE_ANY_TOOLTIP).setControl(TickBoxControl::new)
				.setBinding((options, shading) -> ((SimplyNoShadingOptions) options).setShadeBlocks(shading),
						options -> ((SimplyNoShadingOptions) options).isShadeBlocks())
				.setImpact(LOW).setFlags(REQUIRES_RENDERER_RELOAD).build();
		shadeClouds = OptionImpl.createBuilder(boolean.class, getVanillaOpts()).setName(SHADE_CLOUDS_NAME)
				.setTooltip(SHADE_ANY_TOOLTIP).setControl(TickBoxControl::new)
				.setBinding((options, shading) -> ((SimplyNoShadingOptions) options).setShadeClouds(shading),
						options -> ((SimplyNoShadingOptions) options).isShadeClouds())
				.setImpact(LOW).setFlags(REQUIRES_RENDERER_RELOAD).build();
		shadeFluids = OptionImpl.createBuilder(boolean.class, getVanillaOpts()).setName(SHADE_FLUIDS_NAME)
				.setTooltip(SHADE_ANY_TOOLTIP).setControl(TickBoxControl::new)
				.setBinding((options, shading) -> ((SimplyNoShadingOptions) options).setShadeFluids(shading),
						options -> ((SimplyNoShadingOptions) options).isShadeFluids())
				.setImpact(LOW).setFlags(REQUIRES_RENDERER_RELOAD).build();
		shadeAllGroup = OptionGroup.createBuilder().add(shadeAll).build();
		shadeAnyGroup = OptionGroup.createBuilder().add(shadeBlocks).add(shadeClouds).add(shadeFluids).build();

		groups.add(shadeAllGroup);
		groups.add(shadeAnyGroup);

		return new OptionPage(SHADING_NAME, ImmutableList.copyOf(groups));
	}
}

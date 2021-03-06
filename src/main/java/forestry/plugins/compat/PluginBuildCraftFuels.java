/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.plugins.compat;

import forestry.core.config.Constants;
import forestry.core.utils.ModUtil;
import forestry.plugins.BlankForestryPlugin;
import forestry.plugins.ForestryPlugin;
import forestry.plugins.ForestryPluginUids;

// TODO: Buildcraft for 1.9
@ForestryPlugin(pluginID = ForestryPluginUids.BUILDCRAFT_FUELS, name = "BuildCraft 6 Fuels", author = "mezz", url = Constants.URL, unlocalizedDescription = "for.plugin.buildcraft6.description")
public class PluginBuildCraftFuels extends BlankForestryPlugin {

	@Override
	public boolean isAvailable() {
		return ModUtil.isAPILoaded("buildcraft.api.fuels", "[2.0, 3.0)");
	}

	@Override
	public String getFailMessage() {
		return "Compatible BuildCraftAPI|fuels version not found";
	}

//	@Optional.Method(modid = "BuildCraftAPI|fuels")
//	@Override
//	public void doInit() {
//		ICoolantManager coolantManager = BuildcraftFuelRegistry.coolant;
//		ICoolant waterCoolant = coolantManager.getCoolant(Fluids.WATER.getFluid());
//		float waterCooling = waterCoolant.getDegreesCoolingPerMB(100);
//
//		coolantManager.addCoolant(Fluids.ICE.getFluid(), Constants.ICE_COOLING_MULTIPLIER * waterCooling);
//
//		Fluid ethanol = Fluids.BIO_ETHANOL.getFluid();
//		if (ethanol != null) {
//			int ethanolPower = 40;
//			int ethanolBurnTime = Math.round(Constants.ENGINE_CYCLE_DURATION_ETHANOL * ForestryAPI.activeMode.getFloatSetting("fuel.ethanol.combustion"));
//			BuildcraftFuelRegistry.fuel.addFuel(ethanol, ethanolPower, ethanolBurnTime);
//		}
//	}

}

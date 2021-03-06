package forestry.arboriculture.blocks;

import com.google.common.collect.Maps;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import forestry.api.arboriculture.EnumForestryWoodType;
import forestry.api.arboriculture.EnumVanillaWoodType;
import forestry.api.arboriculture.IWoodType;
import forestry.arboriculture.IWoodTyped;
import forestry.arboriculture.blocks.property.PropertyWoodType;
import forestry.core.config.Constants;

@SideOnly(Side.CLIENT)
public class WoodTypeStateMapper extends StateMapperBase {

	@Nonnull
	private final IWoodTyped woodTyped;
	@Nonnull
	private final String blockPath;
	@Nullable
	private final PropertyWoodType<?> propertyWoodType;
	@Nonnull
	private final List<IProperty> propertiesToRemove = new ArrayList<>();

	public WoodTypeStateMapper(@Nonnull IWoodTyped woodTyped, @Nullable PropertyWoodType<?> propertyWoodType) {
		this.woodTyped = woodTyped;
		this.blockPath = woodTyped.getBlockKind().toString();
		this.propertyWoodType = propertyWoodType;
	}

	public WoodTypeStateMapper(@Nonnull IWoodTyped woodTyped, @Nonnull String blockPath, @Nullable PropertyWoodType<?> propertyWoodType) {
		this.woodTyped = woodTyped;
		this.blockPath = blockPath;
		this.propertyWoodType = propertyWoodType;
	}
	
	public WoodTypeStateMapper addPropertyToRemove(IProperty property){
		this.propertiesToRemove.add(property);
		return this;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
		final Map<IProperty<?>, Comparable<?>> properties;
		if (propertyWoodType != null) {
			properties = Maps.newLinkedHashMap(state.getProperties());
			properties.remove(propertyWoodType);
		} else {
			properties = Maps.newLinkedHashMap(state.getProperties());
		}

		for (IProperty property : propertiesToRemove) {
			properties.remove(property);
		}

		Block block = state.getBlock();
		int meta = block.getMetaFromState(state);
		IWoodType woodType = woodTyped.getWoodType(meta);
		if (woodType instanceof EnumForestryWoodType) {
			return getForestryModelResourceLocation(woodType, properties);
		} else {
			return getVanillaModelResourceLocation(block, woodType, properties);
		}
	}

	private ModelResourceLocation getForestryModelResourceLocation(IWoodType woodType, Map<IProperty<?>, Comparable<?>> properties) {
		String resourceLocation = "arboriculture/" + blockPath + '/' + woodType;
		String propertyString = this.getPropertyString(properties);
		return new ModelResourceLocation(Constants.MOD_ID + ':' + resourceLocation, propertyString);
	}

	private ModelResourceLocation getVanillaModelResourceLocation(Block block, IWoodType woodType, Map<IProperty<?>, Comparable<?>> properties) {
		String resourceLocation;
		if (woodType == EnumVanillaWoodType.OAK && (block instanceof BlockFenceGate || block instanceof BlockFence)) {
			resourceLocation = blockPath;
		} else {
			resourceLocation = woodType + "_" + blockPath;
		}
		String propertyString = this.getPropertyString(properties);
		return new ModelResourceLocation("minecraft:" + resourceLocation, propertyString);
	}

}

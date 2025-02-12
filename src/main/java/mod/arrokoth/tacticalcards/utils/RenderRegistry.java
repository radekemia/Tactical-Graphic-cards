package mod.arrokoth.tacticalcards.utils;

import mod.arrokoth.tacticalcards.block.GraphicCardBlock;
import mod.arrokoth.tacticalcards.entity.render.GraphicCardRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderRegistry
{
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer((EntityType<? extends ThrowableItemProjectile>) RegistryHandler.ENTITIES.get("card").get(), GraphicCardRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockRenderType(FMLClientSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            for (RegistryObject<Block> block : RegistryHandler.BLOCKS.values())
            {
                if (block.get() instanceof GraphicCardBlock)
                {
                    ItemBlockRenderTypes.setRenderLayer(block.get(), RenderType.cutout());
                }
            }
        });
    }
}

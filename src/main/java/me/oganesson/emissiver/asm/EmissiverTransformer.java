package me.oganesson.emissiver.asm;

import me.oganesson.emissiver.asm.utils.TargetClassVisitor;
import me.oganesson.emissiver.asm.visitors.AbstractCTMBakedModelVisitor;
import me.oganesson.emissiver.asm.visitors.ModelCTMVisitor;
import me.oganesson.emissiver.asm.visitors.RegionRenderCacheBuilderVisitor;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

@SuppressWarnings("unused")
public class EmissiverTransformer implements IClassTransformer, Opcodes {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        String internalName = transformedName.replace('.', '/');
        switch (internalName) {
            case AbstractCTMBakedModelVisitor.TARGET_CLASS_NAME -> {
                ClassReader classReader = new ClassReader(basicClass);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                classReader.accept(new TargetClassVisitor(classWriter, AbstractCTMBakedModelVisitor.TARGET_METHOD, AbstractCTMBakedModelVisitor::new), 0);
                return classWriter.toByteArray();
            }
            case RegionRenderCacheBuilderVisitor.TARGET_CLASS_NAME -> {
                ClassReader classReader = new ClassReader(basicClass);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                classReader.accept(new TargetClassVisitor(classWriter, RegionRenderCacheBuilderVisitor.TARGET_METHOD, RegionRenderCacheBuilderVisitor::new), 0);
                return classWriter.toByteArray();
            }
            case ModelCTMVisitor.TARGET_CLASS_NAME -> {
                ClassReader classReader = new ClassReader(basicClass);
                ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                classReader.accept(new TargetClassVisitor(classWriter, ModelCTMVisitor.TARGET_METHOD, ModelCTMVisitor::new), 0);
                return classWriter.toByteArray();
            }
        }
        return basicClass;
    }
}

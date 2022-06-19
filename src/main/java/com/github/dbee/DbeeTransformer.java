package com.github.dbee;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.List;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * TODO
 *
 * @author shane
 * @since 1.0
 */
public class DbeeTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if ("com/dbeaver/lm/embedded/LicenseServiceEmbedded".equals(className)) {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassNode classNode = new ClassNode(ASM5);
            classReader.accept(classNode, 0);
            List<MethodNode> methodNodeList = classNode.methods;
            for (MethodNode methodNode : methodNodeList) {
                if("getDecryptionKey".equals(methodNode.name)){
                    InsnList instructions = methodNode.instructions;
                    InsnList list = new InsnList();
                    list.add(new MethodInsnNode(INVOKESTATIC, "java/util/Base64", "getDecoder","()Ljava/util/Base64$Decoder", false));
                    list.add(new LdcInsnNode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAufkSfMRr+e1oDfk1Nc3ce3UvFDbe7vibs8esZPzkW0GUxBb7IBhVo1JPSdOeHnSHUm8TQLAshd8zMb5ohMfygEq50seNSQ+1HE08k47sicnquGRPIIuuicouF5899pIuqvBj/jCImuaw/v2dgacCcmCgV4IsOJ2nyImIdXXbKwUAkQ+QpBU4AJeRN4jPMDV5Vfxn9IXjIXHOik3XIPo9HTfm4rRJOXU468UdeeUdaxqicaIGyCXTU0IXGPX0BqTXWuQ8/f0i/h8sM2Sn/Jq1YZNAmiECTQFeY+y7FkTTQJXLSua2SjyewN1IpO76moQpZ5+lCUi89OHZsOPJ3D9U3wIDAQAB"));
                    list.add(new MethodInsnNode(INVOKEVIRTUAL, "java/util/Base64$Decoder", "decode", "(Ljava/lang/String;)[B", false));
                    list.add(new VarInsnNode(ASTORE, 0));
                    list.add(new TypeInsnNode(NEW, "java/security/spec/X509EncodedKeySpec"));
                    list.add(new InsnNode(DUP));
                    list.add(new VarInsnNode(ALOAD, 0));
                    list.add(new MethodInsnNode(INVOKEVIRTUAL, "java/security/spec/X509EncodedKeySpec", "<init>", "([B)V", false));
                    list.add(new VarInsnNode(ASTORE, 1));
                    list.add(new LdcInsnNode("RSA"));
                    list.add(new MethodInsnNode(INVOKESTATIC, "java/security/KeyFactory", "getInstance","(Ljava/lang/String;)Ljava/security/KeyFactory;", false));
                    list.add(new VarInsnNode(ASTORE, 2));
                    list.add(new VarInsnNode(ALOAD, 2));
                    list.add(new VarInsnNode(ALOAD, 1));
                    list.add(new MethodInsnNode(INVOKEVIRTUAL, "java/security/KeyFactory", "generatePublic", "(Ljava/security/spec/KeySpec;)Ljava/security/PublicKey;", false));

                    list.add(new InsnNode(ARETURN));
                    instructions.insertBefore(instructions.getFirst(), list);
                    methodNode.visitEnd();
                }
            }
            ClassWriter classWriter = new ClassWriter(2);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        if("com/dbeaver/lm/validate/PublicLicenseValidator".equals(className)) {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassNode classNode = new ClassNode(ASM5);
            classReader.accept(classNode, 0);
            List<MethodNode> methodNodeList = classNode.methods;
            for (MethodNode methodNode : methodNodeList) {
                if("validateLicense".equals(methodNode.name)){
                    InsnList instructions = methodNode.instructions;
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode("VALID: Ok"));
                    list.add(new InsnNode(ARETURN));
                    instructions.insertBefore(instructions.getFirst(), list);
                    methodNode.visitEnd();
                }
            }

            ClassWriter classWriter = new ClassWriter(2);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        if("com/dbeaver/lm/validate/PublicServiceClient".equals(className)) {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassNode classNode = new ClassNode(ASM5);
            classReader.accept(classNode, 0);
            List<MethodNode> methodNodeList = classNode.methods;
            for (MethodNode methodNode : methodNodeList) {
                if("ping".equals(methodNode.name)){
                    InsnList instructions = methodNode.instructions;
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode("pong"));
                    list.add(new InsnNode(ARETURN));
                    instructions.insertBefore(instructions.getFirst(), list);
                    methodNode.visitEnd();
                }

                if("checkCustomerEmail".equals(methodNode.name)){
                    InsnList instructions = methodNode.instructions;
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode(""));
                    list.add(new InsnNode(ARETURN));
                    instructions.insertBefore(instructions.getFirst(), list);
                    methodNode.visitEnd();
                }

                if("checkLicenseStatus".equals(methodNode.name)){
                    InsnList instructions = methodNode.instructions;
                    InsnList list = new InsnList();
                    list.add(new LdcInsnNode("VALID: Ok"));
                    list.add(new InsnNode(ARETURN));
                    instructions.insertBefore(instructions.getFirst(), list);
                    methodNode.visitEnd();
                }
            }

            ClassWriter classWriter = new ClassWriter(2);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }

        return classfileBuffer;

    }
}

package org.timemates.rrpc.generator.kotlin

import org.timemates.rrpc.codegen.adapters.SchemaAdapter
import org.timemates.rrpc.common.metadata.RMResolver
import org.timemates.rrpc.common.metadata.annotations.NonPlatformSpecificAccess

public object KotlinSchemaAdapter : SchemaAdapter {
    @OptIn(NonPlatformSpecificAccess::class)
    override fun process(
        config: SchemaAdapter.Config,
        resolver: RMResolver,
    ): RMResolver {
        resolver.resolveAvailableFiles().filterNot {
            it.packageName.value.startsWith("google.protobuf") ||
                it.packageName.value.startsWith("wire")
        }.map { file ->
            FileGenerator.generateFile(
                resolver = resolver,
                file = file,
                clientGeneration = config.clientGeneration,
                serverGeneration = config.serverGeneration,
            )
        }.forEach { spec ->
            config.output.forEach { output ->
                when (output) {
                    is SchemaAdapter.Config.Custom -> {}
                    is SchemaAdapter.Config.FS -> {
                        spec.writeTo(output.path.toNioPath())
                    }
                }
            }
        }

        return resolver
    }
}
package ru.siksmfp.kotlin.streams.archive

import ru.siksmfp.kotlin.streams.context.Configuration
import ru.siksmfp.kotlin.streams.file.HeaderProcessor
import ru.siksmfp.kotlin.streams.util.byteArrayToInt
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

class ArchiveContent(targetPath: String) {

    private val inputStream: InputStream = Files.newInputStream(Paths.get(targetPath))
    private val entryOnNumberMap = LinkedHashMap<String, Int>()
    private val mapIterator = entryOnNumberMap.iterator()

    init {
        while (true) {
            val headerByteSize = inputStream.readNBytes(Configuration.INT_SIZE)
            if (headerByteSize.isEmpty()) {
                break
            }
            val headerSize = byteArrayToInt(headerByteSize)
            val headerContent = inputStream.readNBytes(headerSize)
            val header = HeaderProcessor.deserializeHeader(headerContent)
            entryOnNumberMap.merge(header.relativePath, 1, Integer::sum)
            inputStream.skip(header.bodySize.toLong())
        }
    }

    fun getNextFile(): MutableMap.MutableEntry<String, Int>? =
            if (mapIterator.hasNext()) mapIterator.next() else null
}
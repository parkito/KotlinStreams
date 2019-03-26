package ru.siksmfp.kotlin.streams

import ru.siksmfp.kotlin.streams.archive.ArchiveFileBuilder
import ru.siksmfp.kotlin.streams.archive.ArchiveFileExtractor
import ru.siksmfp.kotlin.streams.archive.ArchiveFileWriter
import ru.siksmfp.kotlin.streams.directory.DirectoryEncryptor
import ru.siksmfp.kotlin.streams.directory.DirectoryReader
import ru.siksmfp.kotlin.streams.encryptor.Algorithm
import ru.siksmfp.kotlin.streams.encryptor.Algorithm.NOON
import ru.siksmfp.kotlin.streams.encryptor.EncryptorFactory

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("Please enter path to folder")
    }

    if (args[0] == "-unpack") {

    }

    val directoryForEncrypt = args[0]
    val targetPath = args[1]
    val encryptAlgorithms = if (args.size > 2) Algorithm.valueOf(args[2]) else NOON
    val archiveFilePath = ""

    val reader = DirectoryReader(directoryForEncrypt)
    val algorithm = EncryptorFactory.get(encryptAlgorithms)
    val compressor = DirectoryEncryptor(algorithm)

    ArchiveFileBuilder(reader, compressor).startBuilding()
    ArchiveFileWriter(targetPath).startWriting()
    ArchiveFileExtractor(archiveFilePath, targetPath).extract()

    println("Archive successfully created")
}
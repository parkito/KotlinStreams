package ru.siksmfp.kotlin.streams

import ru.siksmfp.kotlin.streams.encryptor.Algorithm
import ru.siksmfp.kotlin.streams.encryptor.Algorithm.NOON
import ru.siksmfp.kotlin.streams.encryptor.DirectoryWriter
import ru.siksmfp.kotlin.streams.encryptor.EncryptorFactory


fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("Please enter path to folder")
    }
    val directoryForEncrypt = args[0]
    val targetPath = args[1]
    val encryptAlgorithms = if (args.size > 2) Algorithm.valueOf(args[2]) else NOON

    val reader = DirectoryReader(directoryForEncrypt)
    val writer = DirectoryWriter(targetPath)
    val algorithm = EncryptorFactory.get(encryptAlgorithms)
    val compressor = DirectoryCompressor(algorithm)

    FileProcessor(reader, writer, compressor).encrypt()
}
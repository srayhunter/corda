package net.corda.demobench.model

import net.corda.demobench.AbstractLogging
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.*
import org.junit.Test

class JVMConfigTest : AbstractLogging() {

    private val jvm = JVMConfig()

    @Test
    fun `test Java path`() {
        assertTrue(Files.isExecutable(jvm.javaPath))
    }

    @Test
    fun `test application directory`() {
        assertTrue(Files.isDirectory(jvm.applicationDir))
    }

    @Test
    fun `test user home`() {
        assertTrue(Files.isDirectory(jvm.userHome))
    }

    @Test
    fun `test command for Jar`() {
        val command = jvm.commandFor(Paths.get("testapp.jar"), "arg1", "arg2")
        val java = jvm.javaPath
        assertEquals(listOf(java.toString(), "-jar", "testapp.jar", "arg1", "arg2"), command)
    }

    @Test
    fun `test process for Jar`() {
        val process = jvm.processFor(Paths.get("testapp.jar"), "arg1", "arg2", "arg3")
        val java = jvm.javaPath
        assertEquals(listOf(java.toString(), "-jar", "testapp.jar", "arg1", "arg2", "arg3"), process.command())
    }

}

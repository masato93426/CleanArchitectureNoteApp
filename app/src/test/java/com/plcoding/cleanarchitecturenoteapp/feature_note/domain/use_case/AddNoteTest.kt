package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        addNote = AddNote(fakeRepository)
    }

    @Test
    fun `Order empty title note`() = runBlocking {
        val note = Note("", "content", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The title of the note can't be empty.")
        }
    }

    @Test
    fun `Order half width space title note`() = runBlocking {
        val note = Note(" ", "content", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The title of the note can't be empty.")
        }
    }

    @Test
    fun `Order full width space title note`() = runBlocking {
        val note = Note("　", "content", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The title of the note can't be empty.")
        }
    }

    @Test
    fun `Order empty content note`() = runBlocking {
        val note = Note("title", "", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The content of the note can't be empty.")
        }
    }

    @Test
    fun `Order half width content title note`() = runBlocking {
        val note = Note("title", " ", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The content of the note can't be empty.")
        }
    }

    @Test
    fun `Order full width content title note`() = runBlocking {
        val note = Note("title", "　", System.currentTimeMillis(), Color.DarkGray.toArgb(), null)
        try {
            addNote.invoke(note)
        } catch (e: InvalidNoteException) {
            assert(e.message == "The content of the note can't be empty.")
        }
    }
}
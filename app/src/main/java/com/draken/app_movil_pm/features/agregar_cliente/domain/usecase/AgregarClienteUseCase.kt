package com.draken.app_movil_pm.features.agregar_cliente.domain.usecase

import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.filemanager.domain.FileManagerRepository
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.features.agregar_cliente.domain.repository.AgregarClienteRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class AgregarClienteUseCase(
    private val repository: AgregarClienteRepository,
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(
        claveCliente: String,
        nombre: String,
        celular: String,
        email: String,
        characterIcon: CharacterIcon
    ): Response {
        val claveClienteRequestBody =
            claveCliente.toRequestBody("text/plain".toMediaTypeOrNull())
        val nombreRequestBody = nombre.toRequestBody("text/plain".toMediaTypeOrNull())
        val celularRequestBody = celular.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailRequestBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val characterIconNumberRequestBody = characterIcon.characterIconNumber.toString()
            .toRequestBody("text/plain".toMediaTypeOrNull())
        val image = if (characterIcon.characterIconUri != null) {
            fileManagerRepository.uriToMultipartBody(characterIcon.characterIconUri, "characterIcon")
        } else {
            null
        }

        return repository.agregarCliente(
            claveCliente = claveClienteRequestBody,
            nombre = nombreRequestBody,
            celular = celularRequestBody,
            email = emailRequestBody,
            characterIcon = characterIconNumberRequestBody,
            image = image,
        )
    }
}
package com.draken.app_movil_pm.features.editar_cliente.domain.usecase

import com.draken.app_movil_pm.core.domain.model.CharacterIcon
import com.draken.app_movil_pm.core.domain.model.Response
import com.draken.app_movil_pm.core.filemanager.domain.FileManagerRepository
import com.draken.app_movil_pm.features.editar_cliente.domain.repository.EditarClienteRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class EditarClienteUseCase(
    private val repository: EditarClienteRepository,
    private val fileManagerRepository: FileManagerRepository
) {
    suspend operator fun invoke(
        claveCliente: String,
        nombre: String,
        celular: String,
        email: String,
        characterIcon: CharacterIcon
    ): Response {
        val nombreRequestBody = nombre.toRequestBody("text/plain".toMediaTypeOrNull())
        val celularRequestBody = celular.toRequestBody("text/plain".toMediaTypeOrNull())
        val emailRequestBody = email.toRequestBody("text/plain".toMediaTypeOrNull())
        var characterIconNumberRequestBody: RequestBody? = null
        var image: MultipartBody.Part? = null
        if (characterIcon.characterIconUri != null) {
            characterIconNumberRequestBody = characterIcon.characterIconUri.toString()
                .toRequestBody("text/plain".toMediaTypeOrNull())
                image = fileManagerRepository.uriToMultipartBody(
                    characterIcon.characterIconUri,
                    "characterIcon"
                )
        }

        val res = repository.editarCliente(
            claveCliente = claveCliente,
            nombre = nombreRequestBody,
            celular = celularRequestBody,
            email = emailRequestBody,
            characterIcon = characterIconNumberRequestBody,
            image = image
        )
        var response = Response()
        res.onSuccess { r ->
            response = r
        }
        res.onFailure { err ->
            response = Response(error = err.message)
        }
        return response
    }
}
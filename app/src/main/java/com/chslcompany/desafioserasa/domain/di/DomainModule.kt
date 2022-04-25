package com.chslcompany.desafioserasa.domain.di

import com.chslcompany.desafioserasa.domain.usecase.dbcrud.*
import com.chslcompany.desafioserasa.domain.usecase.getcharacters.GetCharactersUseCase
import com.chslcompany.desafioserasa.domain.usecase.getcharacters.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindInsertCrudDbUseCase(insertCrudDbUseCaseImpl: InsertCrudDbUseCaseImpl): InsertCrudDbUseCase

    @Binds
    fun bindGetCrudDbUseCase(getCrudDbUseCaseImpl: GetCrudDbUseCaseImpl): GetCrudDbUseCase

    @Binds
    fun bindDeleteCrudDbUseCase(deleteCrudDbUseCaseImpl: DeleteCrudDbUseCaseImpl): DeleteCrudDbUseCase

}

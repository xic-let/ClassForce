package com.example.classforce.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos")
data class CursoEntity(
    @PrimaryKey(autoGenerate = true)
    val courseid: Long = 0,
    var nome: String,
    var local: String,
    var dataInicio: String,
    var dataFim: String,
    var preco: String?,
    var duracao: String,
    var edicao: String,
    var descricao: String
) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(courseid)
        parcel.writeString(nome)
        parcel.writeString(local)
        parcel.writeString(dataInicio)
        parcel.writeString(dataFim)
        parcel.writeString(preco)
        parcel.writeString(duracao)
        parcel.writeString(edicao)
        parcel.writeString(descricao)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CursoEntity> {
        override fun createFromParcel(parcel: Parcel): CursoEntity {
            return CursoEntity(
                courseid = parcel.readLong(),
                nome = parcel.readString() ?: "",
                local = parcel.readString() ?: "",
                dataInicio = parcel.readString() ?: "",
                dataFim = parcel.readString() ?: "",
                preco = parcel.readString(),
                duracao = parcel.readString() ?: "",
                edicao = parcel.readString() ?: "",
                descricao = parcel.readString() ?: ""
            )
        }

        override fun newArray(size: Int): Array<CursoEntity?> {
            return arrayOfNulls(size)
        }
    }
}

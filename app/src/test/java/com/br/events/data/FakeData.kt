package com.br.events.data

import com.br.events.data.model.Checkin
import com.br.events.data.model.Event

object FakeData {

    const val IS_LOADING = true
    const val NOT_LOADING = false
    const val IS_CHECK_IN = true

    val CHECK_IN = Checkin(
        "1",
        "Danilo",
        "danilomedeiros.dox@gmail.com"
    )
    val EVENT_DETAIL = Event(
        id = "1",
        title = "Feira de adoção de animais na Redenção",
        description = "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!",
        longitude = "-51.2146267",
        latitude = "-30.0392981",
        image = "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
        date = 1534784400,
        price = 29.99f
    )
    val EVENT_LIST = listOf(
        EVENT_DETAIL,
        EVENT_DETAIL,
        EVENT_DETAIL
    )

}
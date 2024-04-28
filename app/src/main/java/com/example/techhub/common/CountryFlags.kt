package com.example.techhub.common

import androidx.compose.runtime.mutableStateMapOf

data class CountryFlags(val name: String, val acronym: String)

val countryFlagsList = listOf(
    CountryFlags("Afeganistão", "AF"),
    CountryFlags("África do Sul", "ZA"),
    CountryFlags("Albânia", "AL"),
    CountryFlags("Alemanha", "DE"),
    CountryFlags("Andorra", "AD"),
    CountryFlags("Angola", "AO"),
    CountryFlags("Antígua e Barbuda", "AG"),
    CountryFlags("Arábia Saudita", "SA"),
    CountryFlags("Argélia", "DZ"),
    CountryFlags("Argentina", "AR"),
    CountryFlags("Armênia", "AM"),
    CountryFlags("Austrália", "AU"),
    CountryFlags("Áustria", "AT"),
    CountryFlags("Azerbaijão", "AZ"),
    CountryFlags("Bahamas", "BS"),
    CountryFlags("Bangladesh", "BD"),
    CountryFlags("Barbados", "BB"),
    CountryFlags("Barein", "BH"),
    CountryFlags("Bélgica", "BE"),
    CountryFlags("Belize", "BZ"),
    CountryFlags("Benin", "BJ"),
    CountryFlags("Bielorrússia", "BY"),
    CountryFlags("Bolívia", "BO"),
    CountryFlags("Bósnia e Herzegovina", "BA"),
    CountryFlags("Botsuana", "BW"),
    CountryFlags("Brasil", "BR"),
    CountryFlags("Brunei", "BN"),
    CountryFlags("Bulgária", "BG"),
    CountryFlags("Burkina Faso", "BF"),
    CountryFlags("Burundi", "BI"),
    CountryFlags("Butão", "BT"),
    CountryFlags("Cabo Verde", "CV"),
    CountryFlags("Camarões", "CM"),
    CountryFlags("Camboja", "KH"),
    CountryFlags("Canadá", "CA"),
    CountryFlags("Catar", "QA"),
    CountryFlags("Cazaquistão", "KZ"),
    CountryFlags("Chade", "TD"),
    CountryFlags("Chile", "CL"),
    CountryFlags("China", "CN"),
    CountryFlags("Chipre", "CY"),
    CountryFlags("Colômbia", "CO"),
    CountryFlags("Comores", "KM"),
    CountryFlags("Congo", "CG"),
    CountryFlags("Coreia do Norte", "KP"),
    CountryFlags("Coreia do Sul", "KR"),
    CountryFlags("Costa do Marfim", "CI"),
    CountryFlags("Costa Rica", "CR"),
    CountryFlags("Croácia", "HR"),
    CountryFlags("Cuba", "CU"),
    CountryFlags("Dinamarca", "DK"),
    CountryFlags("Djibouti", "DJ"),
    CountryFlags("Dominica", "DM"),
    CountryFlags("Egito", "EG"),
    CountryFlags("Emirados Árabes Unidos", "AE"),
    CountryFlags("Equador", "EC"),
    CountryFlags("Eritreia", "ER"),
    CountryFlags("Eslováquia", "SK"),
    CountryFlags("Eslovênia", "SI"),
    CountryFlags("Espanha", "ES"),
    CountryFlags("Estados Unidos", "US"),
    CountryFlags("Estônia", "EE"),
    CountryFlags("Etiópia", "ET"),
    CountryFlags("Fiji", "FJ"),
    CountryFlags("Filipinas", "PH"),
    CountryFlags("Finlândia", "FI"),
    CountryFlags("França", "FR"),
    CountryFlags("Gabão", "GA"),
    CountryFlags("Gâmbia", "GM"),
    CountryFlags("Gana", "GH"),
    CountryFlags("Geórgia", "GE"),
    CountryFlags("Granada", "GD"),
    CountryFlags("Grécia", "GR"),
    CountryFlags("Guatemala", "GT"),
    CountryFlags("Guiana", "GY"),
    CountryFlags("Guiné", "GN"),
    CountryFlags("Guiné Equatorial", "GQ"),
    CountryFlags("Guiné-Bissau", "GW"),
    CountryFlags("Haiti", "HT"),
    CountryFlags("Holanda", "NL"),
    CountryFlags("Honduras", "HN"),
    CountryFlags("Hungria", "HU"),
    CountryFlags("Iêmen", "YE"),
    CountryFlags("Ilhas Marshall", "MH"),
    CountryFlags("Ilhas Salomão", "SB"),
    CountryFlags("Índia", "IN"),
    CountryFlags("Indonésia", "ID"),
    CountryFlags("Irã", "IR"),
    CountryFlags("Iraque", "IQ"),
    CountryFlags("Irlanda", "IE"),
    CountryFlags("Islândia", "IS"),
    CountryFlags("Israel", "IL"),
    CountryFlags("Itália", "IT"),
    CountryFlags("Jamaica", "JM"),
    CountryFlags("Japão", "JP"),
    CountryFlags("Jordânia", "JO"),
    CountryFlags("Kiribati", "KI"),
    CountryFlags("Kuwait", "KW"),
    CountryFlags("Laos", "LA"),
    CountryFlags("Lesoto", "LS"),
    CountryFlags("Letônia", "LV"),
    CountryFlags("Líbano", "LB"),
    CountryFlags("Libéria", "LR"),
    CountryFlags("Líbia", "LY"),
    CountryFlags("Liechtenstein", "LI"),
    CountryFlags("Lituânia", "LT"),
    CountryFlags("Luxemburgo", "LU"),
    CountryFlags("Madagáscar", "MG"),
    CountryFlags("Malásia", "MY"),
    CountryFlags("Malaui", "MW"),
    CountryFlags("Maldivas", "MV"),
    CountryFlags("Mali", "ML"),
    CountryFlags("Malta", "MT"),
    CountryFlags("Marrocos", "MA"),
    CountryFlags("Maurícia", "MU"),
    CountryFlags("Mauritânia", "MR"),
    CountryFlags("México", "MX"),
    CountryFlags("Micronésia", "FM"),
    CountryFlags("Moçambique", "MZ"),
    CountryFlags("Moldávia", "MD"),
    CountryFlags("Mônaco", "MC"),
    CountryFlags("Mongólia", "MN"),
    CountryFlags("Montenegro", "ME"),
    CountryFlags("Myanmar", "MM"),
    CountryFlags("Namíbia", "NA"),
    CountryFlags("Nauru", "NR"),
    CountryFlags("Nepal", "NP"),
    CountryFlags("Nicarágua", "NI"),
    CountryFlags("Níger", "NE"),
    CountryFlags("Nigéria", "NG"),
    CountryFlags("Niue", "NU"),
    CountryFlags("Noruega", "NO"),
    CountryFlags("Nova Zelândia", "NZ"),
    CountryFlags("Omã", "OM"),
    CountryFlags("Palau", "PW"),
    CountryFlags("Palestina", "PS"),
    CountryFlags("Panamá", "PA"),
    CountryFlags("Papua-Nova Guiné", "PG"),
    CountryFlags("Paquistão", "PK"),
    CountryFlags("Paraguai", "PY"),
    CountryFlags("Peru", "PE"),
    CountryFlags("Polônia", "PL"),
    CountryFlags("Portugal", "PT"),
    CountryFlags("Quênia", "KE"),
    CountryFlags("Quirguistão", "KG"),
    CountryFlags("Reino Unido", "GB"),
    CountryFlags("República Centro-Africana", "CF"),
    CountryFlags("República Checa", "CZ"),
    CountryFlags("República Democrática do Congo", "CD"),
    CountryFlags("República Dominicana", "DO"),
    CountryFlags("Romênia", "RO"),
    CountryFlags("Ruanda", "RW"),
    CountryFlags("Rússia", "RU"),
    CountryFlags("Salvador", "SV"),
    CountryFlags("Samoa", "WS"),
    CountryFlags("Santa Lúcia", "LC"),
    CountryFlags("São Cristóvão e Nevis", "KN"),
    CountryFlags("São Marinho", "SM"),
    CountryFlags("São Tomé e Príncipe", "ST"),
    CountryFlags("São Vicente e Granadinas", "VC"),
    CountryFlags("Seicheles", "SC"),
    CountryFlags("Senegal", "SN"),
    CountryFlags("Serra Leoa", "SL"),
    CountryFlags("Sérvia", "RS"),
    CountryFlags("Singapura", "SG"),
    CountryFlags("Síria", "SY"),
    CountryFlags("Somália", "SO"),
    CountryFlags("Sri Lanka", "LK"),
    CountryFlags("Suazilândia", "SZ"),
    CountryFlags("Sudão", "SD"),
    CountryFlags("Sudão do Sul", "SS"),
    CountryFlags("Suécia", "SE"),
    CountryFlags("Suíça", "CH"),
    CountryFlags("Suriname", "SR"),
    CountryFlags("Tailândia", "TH"),
    CountryFlags("Taiwan", "TW"),
    CountryFlags("Tajiquistão", "TJ"),
    CountryFlags("Tanzânia", "TZ"),
    CountryFlags("Timor-Leste", "TL"),
    CountryFlags("Togo", "TG"),
    CountryFlags("Tonga", "TO"),
    CountryFlags("Trinidad e Tobago", "TT"),
    CountryFlags("Tunísia", "TN"),
    CountryFlags("Turcomenistão", "TM"),
    CountryFlags("Turquia", "TR"),
    CountryFlags("Tuvalu", "TV"),
    CountryFlags("Uganda", "UG"),
    CountryFlags("Ucrânia", "UA"),
    CountryFlags("Uruguai", "UY"),
    CountryFlags("Uzbequistão", "UZ"),
    CountryFlags("Vanuatu", "VU"),
    CountryFlags("Vaticano", "VA"),
    CountryFlags("Venezuela", "VE"),
    CountryFlags("Vietnã", "VN"),
    CountryFlags("Ilhas Virgens Britânicas", "VG"),
    CountryFlags("Ilhas Virgens Americanas", "VI"),
    CountryFlags("Wallis e Futuna", "WF"),
    CountryFlags("Ilhas Åland", "AX"),
    CountryFlags("Yemen", "YE"),
    CountryFlags("Yemen do Sul", "YD"),
    CountryFlags("Yugoslávia", "YU"),
    CountryFlags("Yemen do Norte", "YN"),
    CountryFlags("Zâmbia", "ZM"),
    CountryFlags("Zimbábue", "ZW")
)

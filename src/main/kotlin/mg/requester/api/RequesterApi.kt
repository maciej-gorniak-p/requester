package mg.requester.api

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.Locale
import mg.requester.logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader


@RestController
class RequesterApi {

    @PostMapping("/auth/token")
    fun authToken(
        @RequestHeader(name = "Authorization", required = true) authorizationHeader: String,
        @RequestParam(name = "grant_type", required = true) grantType: String
    ): ResponseEntity<String> {
        logger.info("Received Authorization header: '$authorizationHeader' with grant_type: '$grantType'")
        return ResponseEntity.ok(tokenResponse)
    }

    @GetMapping("/search-offers",produces = ["application/vnd.allegro.partner.v1+json"])
    fun searchOffers(
        @RequestParam(required = true)
        query: String,
        @RequestParam(required = false)
        prompt: String?,
        @RequestParam(required = false, defaultValue = "10")
        limit: Int,
        @RequestParam(required = false)
        uid: String?,
        @RequestParam(required = false)
        sessionId: String?,
        @RequestParam("marketplace", defaultValue = "allegro-pl") marketplace: String,
        @RequestParam("shippingCountry", defaultValue = "PL") shippingCountry: String,
        locale: Locale,
        @RequestHeader("Authorization", required = true) authorizationString: String
    ): ResponseEntity<String> {
        logger.info("authorizationString: $authorizationString")
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.allegro.partner.v1+json")).body(searchResponse)
    }

    @GetMapping("/search-offers-internal", produces = ["application/json"])
    fun searchOffers(
        @RequestParam(required = true)
        query: String,
        @RequestParam(required = false)
        prompt: String?,
        @RequestParam(required = false, defaultValue = "10")
        limit: Int,
        @RequestParam(required = false)
        uid: String?,
        @RequestParam(required = false)
        sessionId: String?,
        @RequestParam("marketplace", defaultValue = "allegro-pl") marketplace: String,
        @RequestParam("shippingCountry", defaultValue = "PL") shippingCountry: String,
        locale: Locale,
    ): ResponseEntity<String> {
        logger.info("Search request received: query=$query, prompt=$prompt, limit=$limit, uid=$uid, sessionId=$sessionId, marketplace=$marketplace, shippingCountry=$shippingCountry, locale=$locale")
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body(searchResponse)
    }

    companion object {
        private val logger by logger()
        private val searchResponse = """
        {
          "id": "16744835482",
          "name": "Ekspres na Kapsułki 11w1 Nespresso Dolce Gusto Tchibo Caffisimo ESE Mielona",
          "view": {
            "url": "https://allegro.pl/oferta/ekspres-na-kapsulki-11w1-nespresso-dolce-gusto-tchibo-caffisimo-ese-mielona-16744835482?utm_source=chatgpt.com&utm_medium=allegro-ai-api&utm_campaign=search-offers&req_id=4b2a5a0d-fcef-4e1e-9cf7-2b55c61f07a5"
          },
          "product": {
            "rating": {
              "averageRating": {
                "value": 4.93,
                "percentage": 98
              }
            }
          },
          "images": [
            {
              "url": "https://a.allegroimg.com/s400x300/114f11/84bf88604b1d8865aec069e61f56/Ekspres-na-Kapsulki-11w1-Nespresso-Dolce-Gusto-Tchibo-Caffisimo-ESE-Mielona",
              "alt": "Ekspres na Kapsułki 11w1 Nespresso Dolce Gusto Tchibo Caffisimo ESE Mielona"
            },
            {
              "url": "https://a.allegroimg.com/s400x300/11a95f/7d81e7e54385be3a22c470a0e5d6/Ekspres-na-Kapsulki-11w1-Nespresso-Dolce-Gusto-Tchibo-Caffisimo-ESE-Mielona-EAN-GTIN-5905575904645",
              "alt": "Ekspres na Kapsułki 11w1 Nespresso Dolce Gusto Tchibo Caffisimo ESE Mielona EAN (GTIN) 5905575904645"
            },
          ]
        }
        """.trimIndent()
        private val tokenResponse = """
            {
              "access_token":"eyJ...dUA",
              "token_type":"bearer",
              "expires_in":43199,
              "scope":"mg_api",
            }
        """.trimIndent()
    }
}


//@JsonInclude(JsonInclude.Include.NON_NULL)
//data class SearchResponse(
//    val offers: List<OfferSearchOffer>
//)
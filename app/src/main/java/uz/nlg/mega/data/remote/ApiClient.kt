package uz.nlg.mega.data.remote

object ApiClient {

    private const val IS_TESTER = true

    private const val SERVER_DEVELOPMENT = "https://megaapi.nextlevelgroup.uz/api/v1/"
    private const val SERVER_PRODUCTION = "https://megaapi.nextlevelgroup.uz/api/v1/"

    fun server(): String {
        return if (IS_TESTER)
            SERVER_DEVELOPMENT
        else
            SERVER_PRODUCTION
    }

}
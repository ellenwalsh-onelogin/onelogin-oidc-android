package com.onelogin.oidc

import com.onelogin.oidc.data.stores.EncryptionManager

class OIDCConfiguration private constructor(
    internal val issuer: String,
    internal val clientId: String,
    internal val redirectUrl: String,
    internal val scopes: List<String>,
    internal val encryptionManager: EncryptionManager?,
    internal val debug: Boolean,
    internal val loginHint: String?
) {

    class Builder {
        private var issuer: String? = null
        private var clientId: String? = null
        private var redirectUrl: String? = null
        private var scopes: List<String> = emptyList()
        private var encryptionManager: EncryptionManager? = null
        private var debug: Boolean = false
        private var loginHint: String? = null

        fun issuer(issuer: String): Builder {
            this.issuer = issuer
            return this
        }

        fun clientId(clientId: String): Builder {
            this.clientId = clientId
            return this
        }

        fun redirectUrl(redirectUrl: String): Builder {
            this.redirectUrl = redirectUrl
            return this
        }

        fun scopes(scopes: List<String>): Builder {
            this.scopes = scopes
            return this
        }

        fun isDebug(debug: Boolean): Builder {
            this.debug = debug
            return this
        }

        fun loginHint(loginHint: String): Builder {
            this.loginHint = loginHint
            return this
        }

        fun withEncryptionManager(encryptionManager: EncryptionManager): Builder {
            this.encryptionManager = encryptionManager
            return this
        }

        fun build(): OIDCConfiguration {
            requireNotNull(issuer) {
                "issuer is required"
            }
            requireNotNull(clientId) {
                "clientId is required"
            }
            requireNotNull(redirectUrl) {
                "redirectUrl is required"
            }
            require(scopes.isNotEmpty()) {
                "You should add at least the `openid` scope."
            }
            require(scopes.contains("openid")) {
                "You should add `openid` scope as part of the scopes."
            }

            return OIDCConfiguration(
                issuer!!,
                clientId!!,
                redirectUrl!!,
                scopes,
                encryptionManager,
                debug,
                loginHint
            )
        }
    }
}

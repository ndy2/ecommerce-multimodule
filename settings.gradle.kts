rootProject.name = "ecommerce-multimodule"

include("service-discovery")
include("api-gateway")
include("config-server")
include("order-service")
include("user-service")
include("product-service")
include("util")

rootProject.children
    .forEach { it.buildFileName = it.name + ".gradle.kts" }


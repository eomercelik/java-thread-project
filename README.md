#Started Example Deeplink To Web URL POST :

url: http://localhost:8080/api/link-converter/deeplink-to-web-url
body: { "deeplink":"ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064" }
result : "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"
Example Web URL To Deeplink POST :

url: http://localhost:8080/api/link-converter/web-url-to-deeplink
body: { "webURL":"https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064" }
result : "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
#Project Information Project Java Version 11

To Dockerize Project; Run "scripts/dockerize_converted_project.bat"
#Technologies Used technologies:

@SpringBootApplication
@RequestMapping
@RestController
@Aspect, @Pointcut, @AfterThrowing, @Around, @Before
@Repository
@HystrixCommand, @EnableCircuitBreaker
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ControllerAdvice
@ExceptionHandler
@NotNull
@Pattern
@Entity
@slf4j
@CrossOrigin
@RunWith
@WebMvcTest
@MockBean
@SpringBootTest
@ActiveProfiles
@Testcontainers, @Container
@ContextConfiguration

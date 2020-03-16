package org.okboom.reksai.dht.metadata.util;

import cn.hutool.core.util.HexUtil;
import org.junit.Test;
import org.okboom.reksai.dht.metadata.domain.Torrent;
import org.okboom.reksai.tool.bencode.BencodingUtils;

import java.util.Map;

/**
 * @author tookbra
 */
public class TorrentUtilTest {

    @Test
    public void testParseTorrent() {
        byte[] metadata = HexUtil.decodeHex(
                "64353a66696c65736c64363a6c656e6774686935333237383765343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642933303a5368616e6963652028496e6e6572204368696c6429202d2043442e6a7067656564363a6c656e6774686937363935343165343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642933393a5368616e6963652028496e6e6572204368696c6429202d2046726f6e7420436f7665722e6a7067656564363a6c656e6774686936343135313365343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642934333a5368616e6963652028496e6e6572204368696c6429202d20496e7369646520436f7665722023312e6a7067656564363a6c656e6774686936393638303165343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642934333a5368616e6963652028496e6e6572204368696c6429202d20496e7369646520436f7665722023322e6a7067656564363a6c656e6774686939323336393165343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642934333a5368616e6963652028496e6e6572204368696c6429202d20496e7369646520436f7665722023332e6a7067656564363a6c656e6774686936313339303865343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642933383a5368616e6963652028496e6e6572204368696c6429202d205265617220436f7665722e6a7067656564363a6c656e6774686939353239363865343a706174686c31383a5b313939315d20496e6e6572204368696c6432313a5368616e6963652028496e6e6572204368696c642934303a5368616e6963652028496e6e6572204368696c6429202d2052656172206f6620436173652e6a7067656564363a6c656e677468693334333938383965343a706174686c31383a5b313939315d20496e6e6572204368696c6435393a3031202d205368616e696365202d204b65657020596f757220496e6e6572204368696c6420416c6976652028496e747265726c756465292e6d7033656564363a6c656e67746869313033323236333265343a706174686c31383a5b313939315d20496e6e6572204368696c6433363a3032202d205368616e696365202d2049204c6f766520596f757220536d696c652e6d7033656564363a6c656e67746869313134333735333865343a706174686c31383a5b313939315d20496e6e6572204368696c6433393a3033202d205368616e696365202d20466f726576657220496e20596f7572204c6f76652e6d7033656564363a6c656e67746869313232333538343065343a706174686c31383a5b313939315d20496e6e6572204368696c6432393a3034202d205368616e696365202d2049276d20437279696e272e6d7033656564363a6c656e67746869313632343430363965343a706174686c31383a5b313939315d20496e6e6572204368696c6433383a3035202d205368616e696365202d2049204861746520546f204265204c6f6e656c792e6d7033656564363a6c656e67746869313136333731313365343a706174686c31383a5b313939315d20496e6e6572204368696c6433383a3036202d205368616e696365202d2053746f70204368656174696e27204f6e204d652e6d7033656564363a6c656e67746869313231353935363265343a706174686c31383a5b313939315d20496e6e6572204368696c6433323a3037202d205368616e696365202d2053696c656e74205072617965722e6d7033656564363a6c656e67746869313036353037333065343a706174686c31383a5b313939315d20496e6e6572204368696c6433373a3038202d205368616e696365202d20506561636520496e2054686520576f726c642e6d7033656564363a6c656e677468693935333035393965343a706174686c31383a5b313939315d20496e6e6572204368696c6432393a3039202d205368616e696365202d204c6f76696e2720596f752e6d7033656564363a6c656e67746869313039393736333665343a706174686c31383a5b313939315d20496e6e6572204368696c6433373a3130202d205368616e696365202d20596f752041696e277420416c6c20546861742e6d7033656564363a6c656e6774686939323438323065343a706174686c31383a5b313939315d20496e6e6572204368696c6435383a3131202d205368616e696365202d205368616e6963652026204d6f6f6b6965204d65657420486f6d65792028496e7465726c756465292e6d7033656564363a6c656e677468693837393730383165343a706174686c31383a5b313939315d20496e6e6572204368696c6435393a3132202d205368616e696365202d20596f75204469646e2774205468696e6b2049276420436f6d65204261636b205468697320486172642e6d7033656564363a6c656e67746869313238323832393765343a706174686c31383a5b313939315d20496e6e6572204368696c6433353a3133202d205368616e696365202d20596f75205765726520546865204f6e652e6d7033656564363a6c656e67746869313032373033383765343a706174686c31383a5b313939315d20496e6e6572204368696c6435313a3134202d205368616e696365202d2049204c6f766520596f757220536d696c65202848616b65656d2773204d6978292e6d7033656564363a6c656e6774686938393937343265343a706174686c31383a5b313939315d20496e6e6572204368696c6434303a3135202d205368616e696365202d20476f6f646e696768742028496e7465726c756465292e6d7033656564363a6c656e6774686933303032333765343a706174686c31383a5b313939315d20496e6e6572204368696c6431353a496e6e6572204368696c642e6a7067656564363a6c656e6774686939353736353265343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772934343a5368616e696365202832312e2e2e205761797320546f2047726f7729202d20426568696e642043442e6a7067656564363a6c656e6774686933353235383265343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772933373a5368616e696365202832312e2e2e205761797320546f2047726f7729202d2043442e6a7067656564363a6c656e6774686937333937353465343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772934363a5368616e696365202832312e2e2e205761797320546f2047726f7729202d2046726f6e7420436f7665722e6a7067656564363a6c656e6774686939343138343065343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772934373a5368616e696365202832312e2e2e205761797320546f2047726f7729202d20496e7369646520436f7665722e6a7067656564363a6c656e6774686938383939303165343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772934353a5368616e696365202832312e2e2e205761797320546f2047726f7729202d205265617220436f7665722e6a7067656564363a6c656e6774686939373032343065343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a5368616e696365202832312e2e2e205761797320546f2047726f772934373a5368616e696365202832312e2e2e205761797320546f2047726f7729202d2052656172206f6620436173652e6a7067656564363a6c656e677468693135323235303165343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733393a3031202d205368616e696365202d205761797320546f2047726f772028496e74726f292e6d7033656564363a6c656e677468693138373737363765343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733373a3032202d205368616e696365202d204920436172652028496e7465726c756465292e6d7033656564363a6c656e67746869313234383133393165343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733393a3033202d205368616e696365202d20446f6e277420427265616b204d792048656172742e6d7033656564363a6c656e67746869313037393037343665343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733393a3034202d205368616e696365202d205475726e20446f776e20546865204c69676874732e6d7033656564363a6c656e67746869313031333837333065343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732383a3035202d205368616e696365202d20536f6d6577686572652e6d7033656564363a6c656e677468693835313138323465343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733323a3036202d205368616e696365202d2041636520426f6f6e20436f6f6e2e6d7033656564363a6c656e67746869313135383237373965343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732353a3037202d205368616e696365202d2049204c696b652e6d7033656564363a6c656e67746869313034363738373365343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7734323a3038202d205368616e696365202d2047697665204d6520546865204c6f76652049204e6565642e6d7033656564363a6c656e67746869313230323839353065343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733323a3039202d205368616e696365202d2049276c6c2042652054686572652e6d7033656564363a6c656e67746869313432363339383765343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732353a3130202d205368616e696365202d204920576973682e6d7033656564363a6c656e677468693935303635363765343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7734353a3131202d205368616e696365202d205768656e20492053617920546861742049204c6f766520596f752e6d7033656564363a6c656e677468693935373033303565343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7734313a3132202d205368616e696365202d20492057616e6e61204769766520497420546f20596f752e6d7033656564363a6c656e67746869313032353236323465343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7733383a3133202d205368616e696365202d204e65766572204368616e67696e67204c6f76652e6d7033656564363a6c656e677468693230333937323665343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7734383a3134202d205368616e696365202d204a65737573204c6f766573204d65202848696464656e20547261636b292e6d7033656564363a6c656e6774686932363431323465343a706174686c32353a5b313939345d2032312e2e2e205761797320546f2047726f7732323a32312e2e2e205761797320546f2047726f772e6a7067656565343a6e616d65373a5368616e69636531323a7069656365206c656e6774686935323432383865363a70696563657331303632303a6d7ddf7ab47292fd02b76e63fa68f4f0ce9a0859e2fd8667b47365c3e99fb33da0b34e83902c41adde857552862a07dd58a4adc5bcf6aff01f7f67c28c79ff98554d6cee7bbad91384dc8ea0ec9b260a3f5fef654537aa17078484186e03b293eb0c0acb81709c1e899350bb575e5c7accc94ce7ed18e23eaa3f0c6da20ebb4397935a32560f5d331a4b939a001c933b767da99ec9a70c410479edc104b5937e71af91bb91537d14871131dd7a9641161bfb91bcfcffd213a979a95bac2ecc555e2e45471a6f26a8607b81746d8d2619082a629634e5d5a595eff787bf29fe6996308826cb32017d2c89378e020a2f06ab4c061b59d5213faf0f2a0aac4d87b0080fc75a88bc15d02435483977c429037d2b0ca83817dd5b023577f856f7da6b5b5c99cfb58f3672ac70bac6e87146a946891f1102e6d883233708a0bfa50a2946281e3403c8ca796c8a6d9aba7223d5a436d19fd60fd94edb9326597dda2011f02ce217a053df11e45267f9f9eef58ef7a11b95f926fa4e4b594bfbc972c0f0953633ec6bc4fa126f5d8097f4bb3ee95bfff9eceac5033ed3c74fb90209ab9030d83007de4118e29d5ad443a01ad2ffc7667a50f195966f53d91a23b16ee274792ad4c0dfd3057f452233434d211619dd90636f1ce1b5d55a62883c61f0838035e462f71557a0e5328357ddb4c2536cfcbd3654a8b6dcc57339b82ab2612279db920e8468aa12750e2b14a406306f4fe284abb6bf8603f8988159d60a5c9983958b075b90929b79f6f63ad0754ac7bbfe60bfea80760e61e2a9b44117ca3a6e4c5d7ab7178b1fa95938df4324a73aa3e67929a9fca0d6d8b931de0c10ad21ab3094a0d17b8c9cef1b147f42c9346bf2777b4ab2392582a87380c468ec04ec034414c555e5189fe979559118d6adca98f1deca4b32a8ac6df68faad5a6d4d87a0accbc66f34571276723be4de59595414bb0266d5da415e2edf9368410fe9718db6cdd61ce406af9ad779f0e2d8639cc44fbc756fbd8d6bc85e26639936dbedfe90b88d9bc0fd6d0ebfe4086597957800308dc9949e01eada9ef64700518d77b723ae767b4f37e822376ec7e135a5c02c85e963ff9b074e296c5b0124cbfaf9ed3b7b95fb3c6fed57ddab0e7313c59e44e821e5131b90f2a699c2528ceb742243b5841522f0a61dd548ed681ac4a519e4b10339da5b0a5e311f6599d182d9bc7c82573168535eba7681dfa05a26bc32a3fd07b84a3ba3e350150f25a1962d44036277eef078688b5d359f615eafe6993d0c762a7dc1884237d3be1f33c362f4c71532c174606e50e3399f78ba78b6f1988798c9158cc14a33caa664e2da3b5f4fecf6e2093c89fcc1e45163fb03fc27d3fe187fccf8e34c90f6bc2d20c5991b4bffa5dd9c76e1033bd81a924c2065f29d82981731a5f2b93c8ca0ba1e2e0df48a8159a70e2716e6c0df92deb148a07562ad343b31910ef8fdddefa73c6fb71e5370402b683240575b0c1dbfff63730c5dc60cb10c07adefe2a2d624d9e22299c94244815645fc22df19ac336661683233798d7f507acb0fb818a8865479f2f9581788da5cd25230b7bda9064bcce5b7f2367874f939ae7140e4b7e6fbdb9bb34b4eef8ee053d509d01da5f99d5d701a58af09e3bdf8f314490faced083cf0b8e9bbb46d9a33a1ffdc1be5d8bf9c449b0dcc4482c673fc0627bf09f12e7d7f9dc1cd49c401e528307094c9a401c4ef7ca2616c1b71b003274a9392e8a20eb24f0bea313532ecfafb9019e6dfffa4c356fafa253838f264f331aec7ebf8a117af159d7313a63d004f771f46a65d16d0d0eb25d18d90be18783a5ceb9e2d63a60a60369feb36f6d01e34a284a6c9d8ba63b9ea2a079e12526e53ddddb7d4cff026fdf2985848346131924df0f587982a3183a9400adad10a1db7906e544da15d73361ba3513f7300e57c2ac6bc97d6f15d299c8eab0f0d33a2f5804b2034f62e0982064a767ce617d05faf200e71ed136aebf5ccdd184fc7aed77ea062f118fc5b8811170423d5e31d5fa04dec474adbdda743bbc189bb8a3e0d3fc0b90ac0610faa3a942f0034af1fc1ca035af2b05b7f0b829a9208201ddd1266a37f228a98cd414e72f576b3a24a3ce4e87759f6f410dddfe47355508e59b9b549043d8abc6f64d201ec10cbf2b905d82b0c3de166cac791e8b7838e77829873233123009e050f4aad1928621d73ce531398796dc3a406d5e9b7cdd2824dc15e4df9e8cb3b7c06bbffee450042f2c60b0eb4067cf7b20231e55c0a8663abc99b89dee05416b41fc39c7de3ed0a3fab4564e8bfcbc22ceb39e4e69a18f047b56bfef7ac19ac589baed9f91ed9d590ea328dd8bfbcf9c3ed278ba563f718cfe8135567f6a91a1b0d8fa6f8ff2d022ca22291383049a069a0879697627035bbceb9cb97debebf63994265f77062af60cd67a503e930cfdd006f75cf2899587faa5518c0ae9321d570e519d50299f2637d216be5e76298f67d1736207a7f35b8b312b764152681bb7691dc999513efcf8fe37b1b8c46284862184807892adc0138093516494e8553b859480d78cb823bc64cf9c0236e6668a0828363c218bcca85b11053597f9edeb6e35e28e144c126b0df7416c8b50b561496642e1493bb81445596acefbdd3e6f5bd03faff06c6c0e8bc111543212afbab7c1d280d81636cfe1d0f3cd82f9036173c74cff8a718a92279fe15b02c256b2f7eafe34dc3c9b4522f78b3b9dcdf02fdf683170d1457e674441c8523023a3d1d77b1838ca80a82ececb873ae7c0ed01e49cab4cfd33b9bff66bbe30b637acac9b7e4be25e6b945df15a2d7117c08d2d9da1255e5760f42d3bf733812a3a5fac9ecae17b5beaf269ec87005490e05cd5ae17855de5a6777ae52c81559ac991982e815f990f090faea73eeebdf841519807fdc61cf65084c4604a3345c79f9445ae668539d378a86de6b22968d1334cacdd09418b43af0abfdc3e3a4a7354d8fc40fe48bc959a6abd56b639c63d268a7d4337e1f28cf7e42fbdbaed0d004b415e2f1bcea30376d03ba9c89ec4dbce18660ebb0c1a459c2232a65cf9af240a48a0c8d623bd74e2233d499552828a33f657327b37a9a48064d413272634231886a78851b6a1c44877065cee08a430956758bf367a16e766a62dcd339bec83b6413f3aa362ddd9cbb9bc22a53b44c1c889cea07477098bd497cc8f126cfbed7202afd7e969c0ffb1af4bd11602381d983494ade09d4cc5bcd4ea007d22943e6a4b1376c02866f14d5b3f52e11b9fd6491a236dec87e1e5b6ca2a730a6ebe9838656c59acc4504f005fd7e9573437f3bd9408bc3178ab53775b3cece21b2ee939074563102ca71a851db65429ce759458054136ebd39bdbeb535bdf729ac544e7b2aefaca3067d88551c25a218a84ec21dc2e14854f8ce4bfc8477d1f6d12760a676664b982fa4f31ecf4ec98c20b547071951f75351bea70eadd4ed0850210465a9aba8b17da8c7bc1581e849200f095304cbf2bf0489fd37dfacc6aed070629ac7acf98a25de57d9ce42f4479af376ed5e9df4e9f79a35069d009eaa98edc059b31237ab28a994e47419b21e8fdbaa89d07a00b37c6f60a7a2ea5127bae2e1f70dd46ca6ff2475749385a07e6569a419a12f8d5ceb5364f2f44f8158e2ea74bfb56accc9be6123fc93dc7e5cf59c97ea0af7d00686cb4a908295092a4b8e0a5e29e9f7a5f64f7fa1d648b368e60a3f7ce6420bacd76fbc3300feb412e3f3cc5e2eee7585345e13ef98811e1ad3bace3d2b347b4ba8e497fc1803f7034b40f747d126cdeb7d8d7ce1427d57e0a8fe7203d8deda855c5e731a744697249c5eede2f94cd9dadab456002d26d16db52e7caaf7e1f2ae77530fb36edb46c2823f20e4290dba9aa07451a235026d205d5fab63b7997e15a2e1393897bd9fd4784ff98476f2d65345c1bd60b50174a124b1efc646af7335ab8a6d1ec849f525d6d590d29b9676a2491b927348ad64c222d3d3daf976177a8b3edf4fb4b78f7cd99492f56ea22ad83520cc60049247bca1f2a7e38dc0369d8b42571b2d5ea3cf449425c0a2a09d011a0f2cd23545383822b5d0dfb23089cc52dc2283285bc66546a93ac4212b4b410d7fb19936dca1c6395fa77723faf72195b226270e82c3dcccfa8feb13689156f45f58c862502e8511193d4d0d32c9b7cc0a15401b0c46c02852fb073735fcf8beef6d0cb6a4b951e66e862d132bd5331ac78f3214adf03c23234fd4439f8c240fab7edd11686f228ed34e07ba84b5b1e7d64678545b6e02f17c819b2d7d36a0a5ce4e82782d17c570937a9cf5d6253ece8a314643763d102fcb5fe04d2f0d21afa64c7522c8955c74b7b0608941de38e3fb972021ebabf60e2dd76f726cc39bf2a796f8f3590afc44f87e74ecb74e0a83eb3388d8c6b7b2c1017b95626dc31d4c20736d3265c06acec06e80d9aeba723585106ca0e142d704cdb10f7eb5383885bf0eb6e36255c76a3bf27d0b756523cabc9815837f70d94d61025f467bca074af529a117bd80e50a2da08a69005147878e93dc8c04703ba3cfc5ad0bb261e2c7d61f96ba778afc36a73eb913986c1fdc9bc3c858f9b79d378b74192cabbf56e3eea6e8baa738e9b71e3d621edad80997bae3b238b17358940721b287199ea33cbb936269e7088171aea77e5e84a0e298822b30283777f75489e06922b48f0654d8ab7dc1df28c24e958c62ddd85c3b44693508c4d2e1f255349a74bb653bf97d42cda6acc09103fa6cf6bdfed04a02494f337ee5d5035844a2f565f65480e3edada9e1a42f90209339741686a98a6355937d0642c2bd19b57a03c9ac0eb2bde6104c6a693088013d6ababa505ae475e2ef520161e8d71a6526063c53ea0d373f38186d01d57a9d2cf5e927caae54f104dabb51d682e9a8c5d3f5846c017b735cd5ea45c30ab18722098de2f2f044f334f23857e3fa9303f4af4dd0ccbd6cd9a098e0601de3f16bd8c7fadb08fdb044ef7181b30c9f9132d021b886e631b6169852a60b89948f56c6114a45fa7e6128e0f87002f782c1a293d5e5bdc8b011fe044b109916325c4ba19c3c9cab6916a82aa7cc699fccd1ace1dabda1b256cb5765dd03070a970eed3d70ff19edf5d1ece26fb4718d4a49cfc1505684e69de015d34cf9c2ba0def2009733509d2c1c9b2faf1ea0b92028bf5b73447f6c25697e665358cfa7d96ba31fc4aa8f9671993bd4faa4b8613cdfc33f8d92674b7c85133c3bbdb68e8ddc8c572f7ceb2719696c117733fcdcb43c565d01c3e65d6c5cad84f6b948b5464e36c4aa5512e8d8d21a0e420fca7b06b0fe65172494aa02d4c604eceb3e2972cfb1b0a53f6524a1695bf8d9bb4e99bbe6abc64543414d0f378203a251f760369c98d2eb539787598f9b32b8b49acf54834f051922d1d55084be024f00402fbd730324bbc98653c6ba29616e931a614df5e10a5d0aa29ec3abdde658b3e0694ecdf2cc435bfeca2deedf77d2484297e1245f122ad15321b5033e7c1b7cd78e535830d782d3b9d78e04c978d4435638361a645d898a6af6095f27f723db113c873ac048f5c1d73c8048f24588b9c1a08e79e9ead916aca680d81ec08b5df2850f6bff884132f2ee8e0882711954df8e7223b0af8cc0cc8823c3647bfb65556c2333971f67c8f7a566c29dc20ce832746df2ea2777cfc68f9caac5af71940af72f7401953ccf0795cb0a5d3209ec344c75e978830f0f13cf9af0c49d768069813f3ad7347162eb776502cbead278d0a2a55e0da79e109603f214a79bbe53f81143452ed2195d47f8966b1cd3bb602944d3a2e8a8440f588848ef368ac6ca527b3a74a56dc17d4a18467dacd6fe0e7f9607912ca1f9129f0ce93b1a3608207ec2ce18bb3d441990bdea16595d273d8595bac15d5243b9c8748b1b4e0e893b098671377371eed18754e0314b5f626eb475228b3a2525144c797ddc2719f5200f39da370443ada05be87940f66c58ee24fd66d3b131cd2ac9f14522c735d0fd90fd35c9d4ccbd147f3bcd3cd0c5f499f31e5c15454e7f815281e791e049a49d85ab01277497596bf07da4a275ae26214f0e23f2f987af8b20e1fbf16109e0a3a0662fc07eb36deedd27a11f026e52c7a7ca041fab91fd723c954ce7c058975a43f873a512444bc4afd7e06e13920f1593adf00d0ec47d34620675f4d50755859ea0b5544b8e2dfd1f47440e36bef66154d86454ddf7a91c027582de2c0b65bc5f579ec94032c64ba8cdda539001d36ce227e90278ff8d26e9936856b33cc1652c379435f81f1d435c714071620b2c8f732b724af31a7dba826a01936cfc08c0aa5c4eb203ed7a40f5e00249dd40869cd5a50b089887bb4190b4104710def78abfb3c94491295d9ff4480ac3cf6e47d0c6aa8ffc8396cd8075c5e99e618d5bd6ab679756e4666e23bd3ba2226459f42ba0e66db3ff0d1562c11ff741ce12978c14c5588303df542b70772243204fc63e5c56d1e00fc32a541af0596b85a85bfdd9380247be266b70371b5ce52cf5cad38dded0011fde340c17b9e21f673676aa670558ca9faa9561013f10b7f2656525e8ef887a754b43751fc0888813c734cbbf5591b13fde65f537188dc93ee685a0244b25d9a6131d894270edaa3d5e8591da6d967639520bed7b7f4ebf8c45c0506e08434e7f180e2270fe12422b873fa3b2d96e807d2129f4a0e4969c3ead6e7fd0c801fe0ed32c522bbcb1d82eeaac6117c19ef7d23a473202f92c8b6748e2f3e0dc5159146ee072b2854076ba889d629ed39b0afcf56605562b971b40b8f9080c7b3c0ac2332ad296dd9f5f02e5261bbdab76d2d15e46c639a6e5532060c86a9157408c0c6ae0da85bc5f06d80bdc1566344a31bd0a77d5f5af1363aa70a4a787af29ad79e1ca1fea7c5e94c14b924ce681b70965958f1a98d1b9d765a0a18c45d2ff8148d66e2a5ee974c4f6c8818a1217792cc21bc7d240ee604b2bf448ec64e02af4ffeb6f539be45c63f57ce1d442616c01b7ae6245590e70e35299726ff5f2e4665a1afb07280d1d72686b5a062f2444cb85b2aacb3b7fb04cff5386bc12ae763752abfec10b7b872e5b8c2be1c9bcbfa96c68b790c26df0ec4e575c4c9e5626b38703f7559f028aa1cb8b483a1ab934b4eca24398e38f362a688ef51a79b250320346da3bf10198bbcda0c03fc0b9143bd25f81500239fcdf78563f70af074cd02747ab431007dc94d34028a4ffde7870dd43f26232288ac116e72c484d270f6d837fa7750a2ae470b38331fa718ea32f9f028d47d2223e254da197b9735b1bc2dd7390f9ada95ab26200fb61dfd064885247c49154f766a987cbc11f260a8c26b1fb85bc0f306d36217f7963e6005c531dcb620f6a48fdebcc5aebcedab206c235d8c9e5315d336d6d93763611df9c45d8408ba98af4ab243ec4d9d0de5bb54c413f07a5690e5c57555ef3393c6961766bfc7093b06120187e2bc4ab4122b34202253e256282d44bdf3c1811ba9c271e4d8b9586ac56e6a2e0a15470602d4f8395fcb401b60ce18968c82256b88abf06fb9af5799d29269ade59fb28360ea2a5e3ebd3464929384179e1f49ec09c6305a7ff72beb9ffdfec4fd7800e6047ce32e75ef4448d4cb71c7daa6525ec9bf99cbf9eb0d1c1ceb7440a86a4375cc98b8a6cbb26b5069e66de5f120b03e68fad4fe8aa02ac7b29491975f3919a5cb6f8e2129e75e34894bb51e69350a21358c4a0448651900f1a9a85222635403102235fafa2c9d3665a40fe75343c28e98827b22f0efdb448534b4b3cde41d72af2c34eecc2be09d8c23172fb8297199e9fc932973b5e907668db8bf70931eefcef9750363d993c569978a0102f728c8808990278d6e626e08c119915ccee7ed03cebbfa891d1e5b0f2339edaaae60a4c1c0a1ac7b571f0acefc569c8a125b595284ac150b30cc6213054e18949bca89e9dee6415ac026a5cd246fd8d61fc514203b134f10b4aa62247a0ef0870586095025c0265a2136d5b59a180520209772a2548e464c171416402bacd9215d9d55b184494d64f0738da401f0cef31a790bbf57e46dab427eabbbc08beb5eb62055d0ed5844195c041a43f80c3a99ce0648d4ee8015596903de04c816f5ab2aa0d9dc7f590f21cfbd3ca77757eeed165437ce1e4194c1a3a1ec5f21993d082937bca2357fce234c06bc110158cb2b059a817aa79fbf6c5e8b6bb85a90bdf2f6ca71d8d627809b9ddea4433b19f70df3ac09742522bfc4299c3a782cb0d02913fc2f7fed7bd4c435c9769145eb6a479e880e1a59e6957383edaf78ad5ab8f877c498a200c603c1573b711dd88efd2f284d1745400d88634a0d0698ff0315f357e6cb8542026505007c76b3a44eff6064e00c1b156d1717e7459e7b2f802a5ae8b1d5de919c43bef78c712d55b998aae7ad5db8847c1b6c7d1f9e5c16219af91faabcd40d742322408a7cd5ea21f1d6efe3131ff49a3a4d72730317b4ce4631ecb34b6687eba3bcb4ab0059757d27b9d9ee0d2cb2a53a01c3819a6266528fd12d96a72b5c191de831d88c4029303b4f797627f3882f44fd7c7c563498b8b30da8063ee1f2deb6dc98decb78f571268934ab15a7f197096caf82b32bf770f832e823389907f10409db5e0b742d777de1079b728122cba3af4fbd4c43a8a8a41c46fd46517cabc8a91c18814606e36f7d025198ae8855eccc392dfd8a76b83264ff1cec609a92ed639b86df3211e6df62e720b673599ff67327fda2253ef509afb3fe9b89226b0b316645c92cb53df97c090d36acd42e4ab6678fb067d2980c99cc1fa6e3a69243f71c14711e96931538fa8fa3a28ed325d24659652b916fca4830934278dd64268da4f50246dec16bf0b302c5264b593de2d02c9a5f06fb00030722b49409b703b7726039c65456fe12e8c34a46345484c786eda5a8620eca2593eb0d3c55b777ae215ed7508345cbcd83f91be41c83b185cb9116eebcc3fa24c037b0ac649fbffd31bee82881db69637536bd47f0d03e8d32dacba40ece5d632871ef2eca75eb50e4eb843f74ca58b3f7baf1e867a1b8c34f4a339d3b963aef2ed6a3c8dbdca4fbeec5c1d31e748697d65b57e9bb2b13648eabe6f67c325d1745a61b2692c88ac4e04e3eef6c57cd9953eb9f7541525a02876955f19198761f7d28d92e3b7a0053e214ebec043d2a6967ec8c309abcd0e4191613d570263233a1666499ad0cd82d01f07958408e810c2e02119ad86b7485cdfb9c311a4a006967cc163f0541150dfa99ceca7a73473d63f1a7d247a6e193f2071ddb1802e7f37ae6021cf6b309c91fea7a187051e8fd7fb8771b7d4b7bd4bdf2f03f721aca5bcd0c7cbd2fe6559aab849583323c49e44d79f2f45a3a9ec4c360a1bc275e9da983c22a992a70e33df710b7a48ec4470a3da198d6f95fa8612f3ee2950252427ffde1b6d82bdfa6716c5a8cbd52bb0429ff6cf661dae07a33197760813079660775e3baf0387559083704f3a137b0075e57d6b270d62b84f7d4f18d9177731931038dd527ccc68e45e69281dc5d8ed175308f49aa345760b3850d5f4a2f84042d9b89da26f3e9896eed72a2186189bd65ec06d46c768b69c53e43b96b182993c06d6ecad07d7468d4b4379f009adfa329d724552e20aca69ffd8c85c4b068fbf4c6a0dffd124703865671544556745eb7f04772290043a31170e53483e4e6d68db78416a619fbd52423154d290f0aaaf52ead340df77cf3cd085eddb4317026a468a0f869d195d0ea1b3766b1def198eeb6dd451398a97a79d5e9133b7ea8bf03ff0829d18d2dcaea9a7e8e04dc1453e8b27813dd9ec3ee360b93107a47ac521a7b721bb491ac8737a8d934a3b3f4532709509760a14429b545e1472d7bc7ca244e1f3aff857e0590e1d76f2c3818c1135431f9599863aead17da9d89b9f45f663566bfc51b7a106568818ba9c6820aa2bc5100513fdbadeeac62ee01d37e339aabe411cde09ae43dd9a09b15ca46e9bb842478f3c5018dc3fe90040ea26699c99e26345619bea76b8db0514970653859b8800598b1cdc190c96b80c57b0793dae9ebcd1d548eabd31e20409e7acc37c65cfeb463bcd276148e4f76c2758f6ac64ff8ec59133c0cead796f5206b1b798874cc4e47f3075d5d83b4a649a2aa8ed69f81cd2a361f595259171d19f72cb438ee2017180c227450503d8fdea78e26c2dc9640e7d8d2b700f5040f231922594e7eff39ed1b7ae7eaf2ab16876c9c5f22178544baf0c3ad563409670051d5c6befc597a4cc151bdac5b1579dae23a59d837f029e17c7c699f47577546946271ace5ca9a0465f5e060be83a62a9cfb78d2381050091fdbebf7b61832f0bce4977c8259069080643240e1dd9290049fae347d72fc6dc28fb747d8d58b0ce0718f23c5b65eda898fab6ea780c287dd649e01669584525541344786c23d9e0812a80619474bd6dd6c8f3307eafc4dc15c3ee27b154f653f1ab1c179cbe35f02a962ba8ea1389b453a3813c80f24038903ff430ac1eeddbc4e2ba023d6f3c0db16812bbb34943141339d399dc77d2637ed4e102330fb1d043abd4c7084964e1b47af5e48ef0efbc2572e99a8c8be0f88c2294bb0459eb73e25bca2298243e9f8057a285aedbe1e8d409f8123be6784c8a8d4586b2dd6c78dce94cfa622932bc478fc144d1c40515f47d62b80cb55633a2e76bfab7ea8df8d48d8e2200bb71b8a34940bdf8f3e2ed64b533d9e8193a5d7b927924fbf502d24b3b512015874d3e0fd720baf52e3eb0ef61344cbfe37f6d2aabd4d1323171ca5a6108b9378acbb403de24d29986b7dbcc5a3021ab319442c1bb1811824fb6aca5e3c138f2f27b5d5b3572779b3655bf217ead8f9119e45fe9b2dc8d929e843851d326d500b9b37a56b835e1c1e7d0241cb347bee7c1a2cec91e9f259feeb8763df631d275ad04d08dd66a71b0651d4273e823702a6dd7c331446967fa73af3eb97d68ec50a19acec5d9136272531008801a91a28d2a7d1bf351738723fa0e492948a014ed58efa734f273759210772b1c430e13db93cd9cad642a7f2ec655fdac081e066d322f778db174c4704b07fe928b632287e2c9d78023c72267fb55632be87cf2cd026f393b4d74a95d887ec61867d4b73f0f941cb55de30f44b8aa81b3d24d6f50d771ba62dc831b9dbe61f8d18c8d89f5b3fa66ec42381475f68faf609156b55517a1e047ee83abf28de5e080d4d710e871170de5876327651b68485ba1158b5dd1d65c1601b40ed473394b83b57327cf878012818ab974d652ee6b1e2fa961fadda5801e79bae40e9a8ef2fdd1a161477966673c19d17925eaaafef43d7e7978b54b908531ff40aa570c2169d4bc73071014c82cfb7b64c5c45061bb78e0e273c416c460f2a06ec4cc6b0fe784a29c7704dfd73ff0e8d75677276f23ab08e976e93f1b97395a70d4469238f843dae8686d7101ff7c52f88f8981b71d17c7649a67b528a206d185af5f90f9127b783399c1224d584505275a9b87f89ef07ae4222a9c839de5cb7a4d9c0847ea0c49ddae7b3d1868c2bb3d717450adee8e87fd19c3cc6898d3b7fe4f2652d49d083de7698cb81e00e5d97b37726f1ac850b0229aceab39d40d0cbffa403696951af8ca3479e0e5876c25f13dd5cfe677e4e364f72b19f7ccc07775f0085b64112df4a7b42fceba98955897bf88473a0b3b46ee0a5fe009b5072fa5a252f5e1e10b71543ff974cfe5314e1651692dd8bfd0f2dadca2d4d045b914725b47dbe66743b983c6f2baad32f6e38d3783d9ff71882c91a4fe2a8add3d8c88dde9cf85436a6c385f2be9b2641c34f5466e625c26dbe8c264047d1e04d80e3da6e50536388f396d4f79cbfe39b2a9014b5d2bef490af311f3a273e088fed3762d2a08e9b85f2decec1ed99696dd90f77305cb295668e10c80bb92d9d17b97de7071e29cbe8f4c9f3e6368657ee398959d8ef8c616ce9f1e8f798e927cb6611b1e92ec3704121499bcad33bb8276d7efbb8badfb915e4ec0d9cd2856380c0442e1ed112c9260f309ee95c9aa35b1e2ee26b1f5bd7d971d075741547b4339a186e29982265183bba4b656f741b7b6b3a687cc5265b7531b8f87eafab3c694fd12d0949e37fdac094b89080ef5de1e2e9a7ef274ce912e94fbecdb91ddba67addfde266ae7eacb0f5213cd73a33ea136c083fb402173ecc4edf5214597f869ceb167566946227496ffcfd477ae43cdb507e4d01432a0b1c898530f6dacb34eb3724d6ef892a85092c51309b80fb6105e92f04758c27734de1812984cfb8646dcea0b2da65c15fa1d8498f3f2940a7f53c13a64e27d09cafe3ccf9d3e123c98caf498d815fe9316388b6f6a7c510466f2930cc5c2b4696848ede19b4e9635a0f31ff099cbfc76f2ec349e7b3a9a1c88f4e249188db9550c083cf9c0bcaa70d8b7a140078a4540a406649cd407f4295f484978cc544c9ed2329b0d6003b1e475b8f06e14e7b6428a8377a5f08d5fade787f642b18e0b18fc435c04c92f728a51c5cc3d7263b066c75dcac1d61afae984cd8c48b7ba8dc9e70af8d5e8d10fdeb77c051611f8b144c4c20fd630dec74364b84c1ae12c24d0a72f348b16af3772ee2c32e5fe04051858b614f2a7da44e9114ce1eb94c5c0b7494a9f30e915bf64125c946d77c75998a0c4555f7bf9d997570a57201695538b3290c6f083261fb1d0cdf5f0ca3b9757d75d2d92aa5812b1ae53b0dfe4eb1baa89fd0a728fd57003247bf20d7f568698eb77c5035bd68b1993045a6154e771a32c425bfaef6b1cf0100102d4b566193325a7455e880f9dc5f384101dd483f161f5fa1764e1ba6b3ced7db2e7bd0ff0eabc08d76d4f766da120b9a52096c862a76ad633495d9cdfc081f969bd0a41b7a33873366c56eff3e4fbbb7f3fb70338b3ce77a7a9c247c17c74546be4aeee3797d07684162a1a2989315282bb66272c2c27f29da3794f5826e3249c058663e38e80333742fc2ae580a0de5b71b008323e054b94e7a7f9c11089c95e1d9d68469fd120ea41e23781b017454ebadf6cd233d99512e6ab5e01fc5193f413eec94bbd2c21952c7d355f78cb6732aa14e817b07ffb31aa4136d611f80639ee9bef5a8a959a4f6a0353fd8ecfa30307ed87bed1ac7bd5c25477e392b72790e7a1895420f27c88ecec0c60f2ce8c5e83d2e733130befed79c8684312b373bd461637afa3e7b07a828aaea88f1a6e5628243c202dd2d324b928aad1426a0a3c90ab46ade73b5fe1851eade6a6fb6804a2d6e3d1fc10c6cbf20a470842496af7764f2b2860bcc5247e4c6a61e2e58cfa15b9fd7c488d98c73076681310754954ef6568d593f417c5d8d47ac987e8417886ce92e56d1a4239cb29940010863c5b2eb14b48c21fc3c1930962497770fd243c41464270d709083de1135fee3405c0b284b072b970438ecfd6acccf4e90a218df4e68868e1f4933fb75bfc21c73e6e27aec79fc68f16896f4fd87ad154f1461d37bb759bd1235fab652142417ece01b1332068b5e43c3ccd041873f54b258064ba665f89c715eb2026cda8539469201ca702be56df2805d8ba2d731ab947b4ba61b7f6f92a1bc64a13ddf38871c83b90079bc0509d637b75775de8c98e965a970c299e78affc27e91cd407c7b44f13a42d61215c4e5b6f4934a7deff5d57aedf6b6920a93883d11b9b6d0be3b506bf84679549d3f9f840d70e0f6bae5d13a14e49bf907b959e6f560de2a627fb95f6664acdda17c884964c59c3152e2adc87a2b23808b4633e84cb1d433cce8a827ec050f24759d47658c80383bf6e05c4117a7b1fc59867b1dd505ebb932d918ac42752de9bab3ba427be6d60ee284fc749cd3615e006122e263a062612130208abff5fd5a9c5caa6a28016091989ab7e46121702fecd5ab02c06131730641caca71857fbd2d133571c3c9e3c356162d4be8f06c61bdcb2fb2fec95317920c355c21b4c66456a4cbe208bfae3c684c29fbc93e31d1afd1ddd95f5ecbc88bdcae2b82076dca222a9ed7d648162c3ab9a70a2aa95efd1caec02a9b5bdfa98b29b3f46ab7882e8654d8b1a7d12e4b674940437f1fcb3ae4428b95c6ddb8cc6fab938a2c1e771424fada0d81346bacc3b89b2e5bd3a20ad89bac39cc9799829d7c21453e09ac49e5492623ccb394595fdc968a1379465b245ae1cf47ae04c161a7a25ea54aaf792683f3a0b920b59a34e9bc800ec15e883f994b20b87ed3510e9ddecbb0312bb1c278db36f8552dac3600bcf06136e6087d51adf32bbe64daba30f1feb3bac304c3da587a61d468b0a08a62b916d83f7f6138776c26484c7d9f04d0cec5ef1a449282e41448983c238e2268fabdea9d800e281f69d9636472a2d913ce98c76d92fdadd6f7508d1d7e3267a0c25d7c0b69d1e896704fb86b2d47703396bfb2d0f159b6924b35ee84b4d193960e55271c7220efe28545fbef56ed876d9d14b7cded7445f151636ffd81ef36c59e58b02edbab751ec59d408af8b7836570cb8ba4864dc82cb9c661e9d3db2df69fd7a7df0c687da5f4ffce7c4994500a42fe45867e7ced99eb300a3d6f115477c01c017da5fbcdde9f3525de8889d552fc24dce8ff1c787e1739c356334788a5cc25f81414b262d7a08a903818bdf77fabc8964dc20c55f40bd5365bb26cba27a9d23e87df0efa790446c046bd83078f4ed1a3d5f2513648eab6000d8a131a32efc9269e96715ec5a580f5791ba2c773ae7190a345eb6ce0753bd42b8090f38d8150fcaef76ffa67e5d749a35a721eb4a70dde07c31f5d910902be77368a24ccc08ae6644fe47da8da3082b7446599f050c6adb9ac72e58d3ff5024f6bcd5ecea33a1d9301f96b33b9abdf68303ac15bb65f282c0a57cb54723f65e02cca4e2f032eddd007492e864662c4a8d03bb039b8a64cd68dac963ba1024f5261f93fd2c84aeffef68effb64d4d865");
        Map map = BencodingUtils.decode(metadata);
        if (map != null) {
            Torrent torrent = TorrentUtil.parseTorrent(HexUtil.decodeHex("42d5bb189d0ce71ce66cf14220483e163805b73c"), map);
            System.out.println("torrent:" + torrent.toString());
        }
    }
}
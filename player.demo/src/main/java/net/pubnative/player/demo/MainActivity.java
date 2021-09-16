package net.pubnative.player.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import net.pubnative.player.VASTParser;
import net.pubnative.player.VASTPlayer;
import net.pubnative.player.model.VASTModel;

public class MainActivity extends Activity implements VASTPlayer.Listener,
                                                      VASTParser.Listener {

    private static final String TAG = MainActivity.class.getName();
    private static final String VAST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><VAST version=\"3.0\"> <Ad id=\"20004\"> <InLine> <AdSystem version=\"4.0\">inmobi</AdSystem> <AdTitle> <![CDATA[InMobi Test Ad]]> </AdTitle> <Description> <![CDATA[This is sample video ad tag. This tag can show a companion ad while showing video ad on the player.]]> </Description> <Impression> <![CDATA[ https://i.l.inmobicdn.net/studio/asset/ce29fe1a-0a74-4167-82c8-cc115c8263e5.gif ]]> </Impression> <Creatives> <Creative id=\"5480\" sequence=\"1\"> <CompanionAds> <Companion assetHeight=\"200\" assetWidth=\"250\" expandedHeight=\"250\" expandedWidth=\"350\" height=\"250\" id=\"1232\" width=\"300\"> <StaticResource creativeType=\"image/png\"> <![CDATA[https://supply.inmobicdn.net/sandbox-prod-assets/Inmobi-Creative-568x320.jpg]]> </StaticResource> <CompanionClickThrough> <![CDATA[https://www.inmobi.com]]> </CompanionClickThrough> </Companion> </CompanionAds> </Creative> <Creative id=\"5480\" sequence=\"1\"> <Linear> <Duration>00:00:16</Duration> <VideoClicks> <ClickThrough> <![CDATA[https://www.inmobi.com]]> </ClickThrough> <ClickTracking><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=8&ts=$TS]]></ClickTracking><ClickTracking><![CDATA[https://c-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATASXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOAA/821f529a?at=0&am=7&ct=$TS]]></ClickTracking></VideoClicks> <MediaFiles> <MediaFile bitrate=\"500\" codec=\"0\" delivery=\"progressive\" height=\"300\" id=\"5241\" maintainAspectRatio=\"1\" maxBitrate=\"1080\" minBitrate=\"360\" scalable=\"1\" type=\"video/mp4\" width=\"400\"> <![CDATA[https://supply.inmobicdn.net/sandbox-prod-assets/Inmobi-Creative-568x320-15sec.mp4]]> </MediaFile> </MediaFiles> <TrackingEvents><Tracking event=\"start\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=18&ts=$TS]]></Tracking><Tracking event=\"start\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=10&__t=$TS&ml=$MD]]></Tracking><Tracking event=\"firstQuartile\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=12&q=1&mid=video&__t=$TS]]></Tracking><Tracking event=\"midpoint\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=12&q=2&mid=video&__t=$TS]]></Tracking><Tracking event=\"thirdQuartile\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=12&q=3&mid=video&__t=$TS]]></Tracking><Tracking event=\"complete\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=13&mid=video&__t=$TS]]></Tracking><Tracking event=\"creativeView\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=1&__t=$TS]]></Tracking><Tracking event=\"pause\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=14&__t=$TS]]></Tracking><Tracking event=\"resume\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=17&__t=$TS]]></Tracking><Tracking event=\"skip\"><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=99&action=skip&__t=$TS]]></Tracking></TrackingEvents></Linear> </Creative> </Creatives> <Error><![CDATA[http://et-eus.w.inmobi.com/c.asm/HDa26qjetruVBRbmuwcUXBSUARXkCRsBWBgkYjc3ZmY4ODgtM2E0Yy00Y2VkLWIyNmYtYmUyNGE0MTkzZTFkHBUEFRgVAhXYBBUCJQAoA2RpcgATACXKASbAhD0cHBb_v6fo36ij7C8W5_3__9-bqP9bAAAVGBcAAAAAAIBMQBIUABwSGQUAFsCEPRgDVVNEFoq5o5rVVBn1FP6rAZisAbasAcq7AZLLAZTLAfbLAYjMAZrMAZzMAaDMAcjMAczMAdbMAdbTAeDiAfzrAeaZAuDXAriHAyEYXAwAAQsAAQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAAgAAAAAAF2NsAAQAAgAAAAAAAAAACgAEAAAAAAAHoSAKAAUAAAAAAARZSAIABgAAOBcEAAFATIAAAAAAAAQAAj-SbpeNT987ABjCAQMAAQ4IAAIAAAARBgAFAeAGAAYBQAsABwAAAAUzLjAuMAsACAAAAANBUFAIAAkAAAAEAgAKAAQADD-EeuFHrhR7CwAPAAAADE5PX1RBUkdFVElORwgAEgAAzGsLABQAAAAgMjdkMzQ3YTQxZmY5NDY2YWE1YWExNjUwNDA4ZGZkZjECABUACAAWAAAAAAsAFwAAAAU0LjQuNAIAGAACABkAAgAbAAYAHAAAAgAdAQIAIAACACEACgAlAAAAAAAAAAAAGJcCCgABFNUDrtEvGUoLAAIAAAD4CwABAAAAFmNvbS5rYXJtYW5nYW1lcy5ldWNocmUDAAIABgAGAAALAAcAAAAEUlRCRAsACQAAACEzMDgyODc6U0VBVF9JRF9JTk1PQklfU0FOREJPWF9EU1AIAA4AAAAACwAPAAAABzQ4MFgzMjAGABAAIAsAEQAAACA4OGIzN2VmY2Q1ZjM0ZDM2OGU2MDMxN2M3MDY5NDJhNAoAEgAAAAAAq_JsAgAUAQMAFQEDABcFAwAYAAYAGQAECwAbAAAAFWNvbS5zYW5kYm94LmJ1bmRsZS5pZAYAHAAABgAdAAACAB4AAwAfAAIAIgAKACMAAAAAAAAAAAAGAAQAIAgABQAAAAAAABjJATaKuaOa1VS4BFZBU1QcPBwW_7-n6N-oo-wvFuf9___fm6j_WwAWASIAADn1FIjMAcjMAcq7AczMAZLLAZTLAdbTAdbMAZisAZrMAZzMAaDMAeDiAeDXAuaZAvbLAbasAbiHA_zrAf6rARwArBUAACwVAACFABIYCDAuMDAwNTAwHBb_v6fo36ij7C8W5_3vwMqbqJNbACXKARUKOCAyMDQ0YTZkMDFkYTY0NWVmYWM2Y2M4OWZlYjg2ZDQ0ZDQEdwAAAAAAAAAAABgBOBwYFMeNiccEIOhWvQsIn1Bs_LUcaMNDAAA/58deb27c?m=99&action=vast-error&label=[ERRORCODE]&__t=$TS]]></Error></InLine> </Ad> </VAST>";
    private VASTPlayer player;
    private Button openButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openButton = findViewById(R.id.btn_open);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdActvitiy.class));
            }
        });

        player = (VASTPlayer) findViewById(R.id.player);
        player.setListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        player.setLifecycleState(VASTPlayer.LifecycleState.OnResume);
        player.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        player.setLifecycleState(VASTPlayer.LifecycleState.OnPause);
        player.pause();
    }

    public void onLoadClick(View view) {

        Log.v(TAG, "onLoadClick");

        new VASTParser(this).setListener(this)
                            .execute(VAST);
    }

    public void onPlayClick(View view) {

        player.play();
    }

    public void onStopClick(View view) {

        player.stop();
    }

    // VASTParser.Listener
    //---------------------------
    @Override
    public void onVASTParserError(int error) {
        Log.v(TAG, "VASTParser.Listener.onVASTParserError: " + error);
    }

    @Override
    public void onVASTParserFinished(VASTModel model) {

        Log.v(TAG, "VASTParser.Listener.onVASTParserFinished");
        player.load(model);
    }

    // VASTPlayer.Listener
    //---------------------------

    @Override
    public void onVASTPlayerLoadFinish() {

        Log.v(TAG, "onVASTPlayerLoadFinish");
        player.play();
    }

    @Override
    public void onVASTPlayerFail(Exception exception) {

        Log.v(TAG, "onVASTPlayerFail",exception);
    }

    @Override
    public void onVASTPlayerPlaybackStart() {

        Log.v(TAG, "onVASTPlayerPlaybackStart");
    }

    @Override
    public void onVASTPlayerPlaybackFinish() {

        Log.v(TAG, "onVASTPlayerPlaybackFinish");
    }

    @Override
    public void onVASTPlayerOpenOffer() {

        Log.v(TAG, "onVASTPlayerOpenOffer");
    }
}

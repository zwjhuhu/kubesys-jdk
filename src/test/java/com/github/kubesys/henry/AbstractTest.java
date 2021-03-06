/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package com.github.kubesys.henry;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.VirtualMachine;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @since  2019/7/15
 *
 */
public class AbstractTest {

	public static Config config = new ConfigBuilder()
			.withApiVersion("v1")
			.withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1EY3pNVEUwTkRVeU9Wb1hEVEk1TURjeU9ERTBORFV5T1Zvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBS3JDClBCM09oZHI3YTRaUzk4VDdiaTQ2S3RwZVF1eWM5TDUwcjNTcWZQRFZBYlk4cDlGd0U5SDQrV1FGSG5sckVVdHIKU3ozNzVzTzBnWG9RQzhuT0VPRm9oTnV3NzlTaHZsdVNrUW5XUE1LT2VGYUlyYkNTYjBETzU1TUdmVXVXQzZkNgpFOW5yeFB4dzVWZDBucWxVMVUvdUxYY3REL1ZaMlZGYVJpSUtTWXY4ZjlVQjNURUtNZXhoZlFod1FHUDQ5SlFVCnNSOC84QUNzQ1Vkamk2Z0lwYVp0TXFCdU9OYUdsOHpqeTh0QnFnN3lIYWV2WUdxUVFJNTh1d3V0aUdOZmlzYWsKVlVIMFp2RVZIWThaWkdPN0ROYXdON3NOTmdGR1RaYXI4Ri9tc21vSVpESDNyY2d5YTZZV1VVZ0ZKQ3FWU1VGSQpLOHlpcm5JVUpybmVZcU9qN2ljQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFCRVQ0Slc2VXBhbEsyTE5kTmgzbThJRktJZSsKazRmVkdOajdtZGE2emZXOHhKVG1oQlRqNmV3bXFnNDJGdS9LVmgvUjY5SW1ZYjN5b0JZQ01HdXNuODhRQ3Bwcwo5b0lYM1kyZzdWYXZJMWVCU0RzbEd6bURxMDJRZEQ4WWFYTU1qOHVHZksyTE1BNXFmbWpuQ1VRUzFsZHJMdUVuCjZhS1FNdU5pZjV6NGkxWmI2K0pJZWFOdHd6NVIvMHFEVkRHbUlacmpqSGNwb0NGdFAwS1ZhT0pWbXptZHB3SWgKc3Q2RDRkK0N2bURtTXJrRjhGQzhuR2hFdFBvUCtNZ0x4YU1nRVA0RTQyWktRN1l4eXdBOTJLVG1TNTNFbEZGWQpSeUczenJRT29icUl0MTdNWWZtLzlDdk9OUmNsNUhac1Vma3NJcDFPNDZVdndmc09UbnJvRmcrVEE4Zz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJQUpnb2t0OFBKVEF3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T1RBM016RXhORFExTWpsYUZ3MHlNREEzTXpBeE5EUTFNekphTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXJrRlh0WlptaWNwZW5HVGMKMlJpWjVZRDh5SWxDSkNlRDMwOEwyVytGaXhMS0lXak1oTGdEMGxFT1hkaWhzU3lnS1VBK3FLWFVsU3hIc2lHdgpaZytudERDSFluWVFJWkNYRGk4cVZqTVJ4bzJmK2hEb1c0MW5va1JuUlg5NDNuUE43UHRZbTBBbXI3eGRTUmcxCnlXaG5UNmFpc0tyaEoyTlZGbjJPRXc0Z2p1WnIvbVBpY0ZRN21JTVhVeXNyR1BQMjEzb2lFR0JxN28xQU9VblQKVDlIQWx5VUR4NVR1aFZDQUhvNUpQZTl2bXMxRUdwU0YrdmIvRlMvTlNkM0l3YXdBY3BxNExzM2pUVDFJWDdhdwpVWTB2MXlEWlMvb3B3QVFsT0xBNCttaU1uaG90a1VNUWYyM0tCdTNSTnVkQ1YzUFdibC8vb3ZPb1hJbXpEQmdaClVMYVBvUUlEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFDWTM1czk0YkM1ZlZhMUQ1U1QzTEV3dzBKOGpFNExnRXhzSgpBTWVFeHQvQnNxVmVab2MyOUhoWTArN1RrOFVGZHZQSFo0OGhhK3RnNjV4dmJ5RW42cHBBbVE2Q2Nramh5bjExCmRKZUxWRDRYdlVpR2piNVcwc21obU9hYjJrcGVIWklEWkNSejRHd0RnM3NOaWFWQTVjbGlFVmtVdFd1b2R1NEEKcWlBZUpGc2dUdDR6VVl3OVVucGZXU280LzNIa0ZpaFFPNmVSSVRLdGhzTmpLbm45cFBwOUhXSUlCQkFHcjFDVQpxV0I2NUx0Zld1VGM1aDErYmVwbFhXL0lWVFJOTFN6ZXhkYlgxTHFsbUUxUmpDTEZTc3orNy9HWXlLWnVYTjZpCmtOa3doOVMrZmJxWlo1dDhZdXh3TmlNY0hpMHdPdi9yV0xVMXlqaEZUOUk2OG1Cd3ZjUT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
			.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBcmtGWHRaWm1pY3BlbkdUYzJSaVo1WUQ4eUlsQ0pDZUQzMDhMMlcrRml4TEtJV2pNCmhMZ0QwbEVPWGRpaHNTeWdLVUErcUtYVWxTeEhzaUd2WmcrbnREQ0hZbllRSVpDWERpOHFWak1SeG8yZitoRG8KVzQxbm9rUm5SWDk0M25QTjdQdFltMEFtcjd4ZFNSZzF5V2huVDZhaXNLcmhKMk5WRm4yT0V3NGdqdVpyL21QaQpjRlE3bUlNWFV5c3JHUFAyMTNvaUVHQnE3bzFBT1VuVFQ5SEFseVVEeDVUdWhWQ0FIbzVKUGU5dm1zMUVHcFNGCit2Yi9GUy9OU2QzSXdhd0FjcHE0THMzalRUMUlYN2F3VVkwdjF5RFpTL29wd0FRbE9MQTQrbWlNbmhvdGtVTVEKZjIzS0J1M1JOdWRDVjNQV2JsLy9vdk9vWEltekRCZ1pVTGFQb1FJREFRQUJBb0lCQVFDVW1qTERMOFM0QXpXVgo1OG9TOVpEZ2tKU0NLOU9VNDRIcFlRNjQyZ3NubzlsdWptSERXTzRoWUhDQkJhbzJYdGtpOUorNjBOaXVjam1lCkptM2Urc0RJd0kza0lNejI0c2gvRnlmaXBPQ20wOFkyeWs1dExQVFJBOGFTRlBndHROSkxJN25YZytpNSswZ08KZXEvNzE2WXEzZTlsL0FQUXhEd2UxdW5mUkMvdHpCaTJMazE0N2FnVXcycUEyTFpBM2xLWlVSWDRHOHk3aWsyNQp1YThSb0JRUkI0YTVWdGdiUnhnb3BvNis0dDZ3NTlKam1PeXhJRHFFRldNVkwveXIwalR4L1NCMFpnald4NDZDCno4V293dXl4QlNTVitRSnB5VWtlY2NXY29iOVhKZzhEREx4VVFYZ05IeENaeFJYWm40ZDVTQXpGbXpZb21IUGkKWFhneStzZ0JBb0dCQU0yL3NhZzgyTlEyUy9vYXh5Q1pQeHdhNHBYbEIxaWtESzRoZ1daL0JVUk50MkxWSWkregpmaC8yUGhGdGpmcFZYZXRrRW16RVNGY2J2ajQyNERkZDlURUFzNlNQQ2JINU9TMDZtQkxsZDdVTDl1OTFDRmQ5CjRjSWNtRXlacGpCTmx6aDJtYVNkbzB4WUh5bFVVRForYUVRK3dsNnhndU16cTl1dlNSM2RPKzNoQW9HQkFOalEKaVBDemd0L21RN1JwbC9jV3JXZUdXd3JVSWF6dWx1K2l5bEFzR3g3WUR0WVNpV0tDRWRQUDE1YXFSSjhEc1hKTgpZNEVESTE1L1BaeFBLT1Fad2Y0UFUwQ2lZNFpKemVUM0RvUlJqalhxc1M2bHdhTWZDMkFIUGxTbGxHZU5DN1FECnZ5Wk1yc0VyV2NrUks2OTg0UXBqZUNIbEtRV3NJY2dmdjYwWmRWbkJBb0dBWGY2Wk52YTFTQjJXRUNoem1CenEKaS9oc1UzWlNLTmlwUWxQa1BlTUJrUGJBTk5LUno2RDhtYlZ4SFowakk1NGtUZ2xsc1hMcWFZRXZKRlVGa2NYRwo0aUFqOGFXM0ZDdDdSNEpoUHlDOTAyUW41N1Zibmp6UUNsSHQ4ZkN2ejVsaTRGU3VWRm4vNUR6TjM1KzVGV3dpClRpc3R1UHozZ3J4MFl1UnRUc3RuT1lFQ2dZRUFuWXpUcGMxY01lVTZsN1FQbUo2aS9WSjFmK1hzWHFTMWk0ZEkKNm45U2FkUm8yNGgyeXQxbnBQb29XRFI4VHV5NUdpMUFQRFNTbEExME1NeVpFei9nWndTdDQ0N2RNVzBhTCtLVAphdlYwbUp0bGkxY3A0YVlpZHZKc2ljZ1VramE5SlY5L25JNG1ndnBFY1VhSDd5cVl3UnJNNWh5RzliZENpUkVCCkVRRXA5RUVDZ1lFQXJZTlN5cGZVWVd4NzVWcFRmZXhaaWg1ZUJjMzcwWkt4R1NUNDNnM2tLalhyeEZncktCOUYKOTNHTEJnUnJQeTRiS1NBNzRady9UTFF2Uy82bk96eDVCNlpzazNVMkdQaTdjWGZobVFFdVdmdkZsYmErMHEvVQpwMzU1U3FqV1BOc3hPbVpiTWlEWVhGcGQ3cGpHR09ZMllKQjB0Zm1Vd3hnOXEzc0JBbVFlTEtNPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=")
			.withMasterUrl("https://10.25.0.153:6443")
			.build();
	
	public static ExtendedKubernetesClient getClient() throws Exception {
		return new ExtendedKubernetesClient(config);
	}
	
	public static VirtualMachine getVMByName(String name) throws Exception {
		return getClient().virtualMachines().get(name);
	}
}

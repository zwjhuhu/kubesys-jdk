package com.uit.cloud.kubernetes;

/*
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.VirtualMachine;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDisk;
import com.github.kubesys.kubernetes.api.model.VirtualMachineDiskImage;
import com.github.kubesys.kubernetes.api.model.VirtualMachineImage;
import com.github.kubesys.kubernetes.api.model.VirtualMachineNetwork;
import com.github.kubesys.kubernetes.api.model.VirtualMachinePool;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 * @author wuyuewen@otcaix.iscas.ac.cn
 * @author liuhe@otcaix.iscas.ac.cn
 *
 * @version 1.3.0
 * @since   2019/9/3
 *
 */
public class AbstractTest {

    public static Config config = new ConfigBuilder()
            .withApiVersion("v1")
            .withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJd01EWXhOVEV6TURJek1sb1hEVE13TURZeE16RXpNREl6TWxvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTTBUCnZzdjk4N1JpZzN6STlXcUhEcW45T3J0MTVqczZ0eEtieVZZem4xWnk0Mk9Yc3o2SWFiS3BWYVpNQ0kzbnNIY24KLzdsY20yQnhTeXY5SDFBb0svVnlLd2d4dytyQW5lc0JSSFMyeUgwL2ZOdkFJYjh3cVVIZW9QR1djOXhRS2xiLwpEZFZiNDhCTjA0eUMzMlRXZThKWENnSXh6aXJQdHJTek9YRkxjMVFwVldSeXVycTJ1NFJwKzdLdzByRU53Y2VUCmttbzVFVTc2QllLMnFkVFlMendRdmFlUEhMZDg0VkoyT3RQTlY1SzM1Y3FNd1hiTTR2dFQ0MDF1V2NNWlVMOEEKOFU1YzQ4VkJWeVFyUFZxcStTV3A3TDF5MXU1NEd4ZFg1aDQ1cWI0REtsM2x0WTlLdWtrdVI2SVZRazl6TEJQWQo5eTc0RldYbFliYTZLRFp2WnY4Q0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFMVXoyZzlmd3BMbjRObTg5L3N4RU90cTkrOFkKTkZqQ09BNWtxeUhrNis0Ulo1M3RvL1VZMkR3a21ETzYwdXN0Slg4dGl0akR3SkNWM0gycUtMb1JYWFZWTTdsRwppT1Azd3NnTVNyeFFISmtXVzBqTDYwalZDRndXL21uQmZNZWRMaDc5ZG1rUjRoRkczalpKTVlzaU9McXduL1JtCm5mQ0ZYTTRpakJVL1hocVpkY2hLbVM1aW5xUGxib1NJb0FvV2pmNHdXbjNnT2V6Sk91WFJsa2x4WVFOa2xMdmQKNFFEY0ZGWVhrL21VdGlFQ2h0YXgycUd6WE9KTGRUUzVWamlHcHZoMjNUdzR2ckVYYkpoNGJtVmFET3JkTmYvdAp4WW9yRlRmT0prK0RucjZ6d0dHekF6K0diWUwvNWdlTTBQZXVRT0VtLzdKRHZ6elZPNndnTFZQdytRTT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
            .withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM5RENDQWR5Z0F3SUJBZ0lJRmNPaWE4TEZWT0V3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWdGdzB5TURBMk1UVXhNekF5TXpKYUdBOHlNVEl3TURVeU1qRXpNREl6TmxvdwpOREVYTUJVR0ExVUVDaE1PYzNsemRHVnRPbTFoYzNSbGNuTXhHVEFYQmdOVkJBTVRFR3QxWW1WeWJtVjBaWE10CllXUnRhVzR3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3NrTGVHNXVKNm1MWVcKdy9Mdk1WNUFBUW5MeUlhMVBHd21qdVVwWk5uWmI1LzE4Um5mZFloN0p5VVlZQTFNdm5VVDgyK1gvSitsSWFKMwpIOER5RTlZdGxvdnc1OGM1aEtXNEVpbTFtZ2FGQnR4b29uN1RxZ01sbkIxaEoyTFRjTTRuc2xabFdTS2ZRa3VTCmpLN2lzMGZUeUFRRktLQkFqUUVKT0xYVTZyb0hKWDdsaU9NMHFIVW8rS1Q4dnhvblAreVRYRlJIa3lIWkxvQk4KSjVnaHdWQ1RZRVArQmF4cmltc0sreDdpSW1iK2pBdjNyRzJ6d21KZGlQTDNLNWxRcmNyaVNHK3RRUHVTVWZiSQpDN1I2ZVZwdjQwbGMvam5qWkZoSGRxb2hoNE4wYUxRQ1JTUDRJU0J4SHFiU0R6a1llN1BOZjVERE84VlFuV1JOCjQyU3NVcjRUQWdNQkFBR2pKekFsTUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFDbkxQZzRoYThLeEtyZjVKRE44UVAzWkFjd2tCbUdTYgpvSmRDOUFKNEZldlFJQi80dEtxVyt2NnllV052ZHRBR2svQjBtQUJmRXdGNzlIUmVjbkRlR0tUZnFyTDBHVGtFCkZiSGk1c284TjVVZnh6ZzUrZy9qa2ZkNzNSSmpyQW56K2N5OHZ4UFVzVDRiRHNJS0Jvd09MVFdYQ1crL3lTVUEKV1NHQ0p6eE8zMnJmOGM1cWQ0MnRBMkxZUTNKdXNjVVBCV3B2OVNCQ2daTkx6eldRb2QxeG5xdExXdTlXZHcycQphekVZTnFHdy9RSUVuakhMOUdldEhsLzVzK2lwQmg1S3lNVEYreUlIdEhOSGt2VFNTVUhBYXNhaGFjMVhsYnp2Cm5ucFZicVV6WUxQdkRhQ2UxNno3eVFJc0RGUDZHbFJPeWF3bk9XL21xWExnTVdYNXd0UFRXQT09Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K")
            .withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBckpDM2h1YmllcGkyRnNQeTd6RmVRQUVKeThpR3RUeHNKbzdsS1dUWjJXK2Y5ZkVaCjMzV0lleWNsR0dBTlRMNTFFL052bC95ZnBTR2lkeC9BOGhQV0xaYUw4T2ZIT1lTbHVCSXB0Wm9HaFFiY2FLSisKMDZvREpad2RZU2RpMDNET0o3SldaVmtpbjBKTGtveXU0ck5IMDhnRUJTaWdRSTBCQ1RpMTFPcTZCeVYrNVlqagpOS2gxS1Bpay9MOGFKei9zazF4VVI1TWgyUzZBVFNlWUljRlFrMkJEL2dXc2E0cHJDdnNlNGlKbS9vd0w5Nnh0CnM4SmlYWWp5OXl1WlVLM0s0a2h2clVEN2tsSDJ5QXUwZW5sYWIrTkpYUDQ1NDJSWVIzYXFJWWVEZEdpMEFrVWoKK0NFZ2NSNm0wZzg1R0h1enpYK1F3enZGVUoxa1RlTmtyRksrRXdJREFRQUJBb0lCQUdyM2ljdmxjcDNyNjNOUgpJMVdCbEpxNENncDMwb2lsVzZUTER1SUowY0Ftb1ZFaWtwY2hrK2hDNjR1Z2ZwNERzUzhjeHgrL1NIWmowd0ZrCmZZV1JKa0k3LzZMbENXbHVBQXVKdHJFTE9OOWMwV05LMXJMOXY5V2dYOVhKUkVrc3M1Mkh2bVkwU25ITjltcHgKS2piOFNwbk1jakdBYlZXV0VmNVZXT0VTWHgzdDY0V3dlNVVicmR0bXNYU0w2Unh4c2tTN1NyV2lQdTkwclR1NgowS1pEZGdob3ZOUVdIWHhVcGVmelJaSzNKT0FnazRLUzAzZFc4VEpnOTFsVWhoQXF6UFExenc2cEJneEZmRE54CnZ1NkRUNWFZbkRUbHBmZjNDWVh0TnZmamxIeS9ONkZUREl4cUs0UUtUUnFwa0MxK3pqZ1A2bzRiZndzYVJtK0cKMmIxeThnRUNnWUVBelViMHZBWWxRUWVOQjh0YVd0NTZpSnNqUzFBRVN6TVhNV0lrWE1ua0VLb2pQMHRjYjZ1TApQZ0dTRjh3SWRZRk1Xd0RSUkdYOHROMTZwdEdVQWc2TFNuV05iQzVLLzNFemRBb2xnTzRveTdMWllRazZwY1JmCnJrdUhTdFRIZHp0ZTVJa2kzNGpLdExjY2pBU1JDeERZejRjMllqWDJhcEtHV1pKMnlmU3dHckVDZ1lFQTF6U0kKcHBlSFVhRFdUeFQ2Rmo3U1o4S3k4RUl3YjMzd2FPM0krZ0FlYUVTWWIzNXFBa0UrWVdNd2VyS0JQa1lQNEZWSwppWWVVMHRrc1lpbkFoODFmSGV4Q1oyTXV1SXEybXZ3Zld2aEpUMXRKRzhQRmJRUHFJeGk2cU1WRjNqNWIyeXByCjRtOHh4VVczeWJUWlhDb0dWWStaVXFrUG9hN29QNXJGK3NkSXpnTUNnWUVBa1ZYcmNEUHByR0RzT05pQTRGVWoKN0JQcXFTZTk0b0FKcTYwUWthRmZGMjFlQnpJVC8vdzBXWEpEUFJYMzV6bmtGWW8rbjI2VnZIZ1Nub2xEZ1RGSwo3U3pOTkFxU2pzaFc1WkZKZkQ4aUl4WTlVYlozRDJZNVYrMmhuQm1LSDZDcFRxVEpTL3BOaWlVYTBRZHFxYXNECjFlcWxWbU5lVTVYcUNRRHBtWkxrSEFFQ2dZRUFwcjU2bnZITGxIRFZmSUxaWDBFZnordW9yOFhwTjRuU0s4c0sKTE1MSXpLdFJuUm5LMDZKdnpnK0hzWm43MEIrREQ2T21WRGRzRlk3Vjk5MDM0MEU2VUxvTm5ZUFk0SDRDRE1ZTApHWFpGZnlwUTFDVjFKQW1qY2J3b2JMb2xiNVRMb1Y0V0oyVlduKzVyLytxVGUvakgzYVdjKzlJb1hYTmpXbHoyCngxOS94cmNDZ1lFQXlZRFYxM0NMQ2NFTHVOcDJOVy95UWhBSkRlN0k3Tml2cGowS2xTdHNBbHFMWDV5SlQxeGEKbytERTg2Uk8yQ0pseGVLU0FUV1JTNTVlcXJOYUsreW4wNi9LTWJza1ZBTWFhOWxFa3d5LzVqQ2l6RHVOZk5GZQp1bkgwcHhGNGkxQ0VoWEU5dWlIN1UxbTl1SFZsV2J6Vm1naWxLU1E3Y0RDQmdCeUdzbURHOG9BPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=")
            .withMasterUrl("https://192.168.96.22:6443")
            .build();

//	public static Config config = new ConfigBuilder()
//			.withApiVersion("v1")
////			.withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1URXhPREV6TXpBek1Wb1hEVEk1TVRFeE5URXpNekF6TVZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTk9PCjd1NFBSdGJxaVM3bXlvbEt2UHlYSDFzeWdaRFBiMUZ5QUJPcEZxSnA4WGtQNGQ5Ti9pdTFKVWdOWGJtY3YvYUUKN3pqQllORHVTZXo4TU9kQWdDRFNWZ1RVdFh2T0RJaHk5U0RCM2ZMK0QyaVV4SFNsSWtqanBWRmhXeG9BQlpsWQpSMmhPaXNnUjFhQnYxTSs4VFFiZkFHT25JQmNlM1hudm1pdCtRdjlERkIyaEJOM29ySFpkdFpsRlBhclN4djQyCm1ORFF1eUNmMVc3dDJadWdyRHhCWDZXeEFzRDVyMmtUdU03OTZXWS96L3A5TTRuRzhJRUlOdFZycjlxNEozTHoKRlJ0MHk3Y3ZRSks5TWczUGdlY1haRmYxcEZFTE1ObVZBdXVneGFEZUFqeWVoZVRFdm5ONXlpc2pmVVJJaGluTgptdnlidzBMNmp5YWxUQUVYNXRFQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFIZGNRUXNNSUtETkFBK2wwN1dWS002Y2puakQKaE01K1RTcC9ReEhveDR5cVNsYm9SR25NbGdWM0F5THdJeTZMZ0lxazl2Ui9xai9aK1psU0pkanBQcTdIdXUyeApLREZTeDgzblNZN2laLzhFT1ArMWlsTDRlcXJ2RXJMaFdGSzV6ZndwWTF2dlo1d3F1YVVQcFhoM2dqVk1YR3ZxCjBsU2hJZENROUZXWDJ2b01YQUNMWnZnR1h6bWVjcGt6QlZjaEQ3bXptM0xReVB6Z0JxZGVRNUhKa2tIa1FwOXkKMHhNRVNYbVYycFNkVHkzaDlxOXhQUXYvWGx2UXFIS2FNSFhqalpMT3kxSS80a0VUOU1wZUs0VUpTajlKbkZtVQpvTWUyZGsxa2RHUkNTd01iVng0Tyt2cHNlaHgxd3ZRNXpZK3RoWlZab1RLRjNNR1Z4Q1J4aFZZdXRvQT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
////			.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJRytST3hRS00rTW93RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T1RFeE1UZ3hNek13TXpGYUZ3MHlNREV4TVRjeE16TXdNekphTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXF3b0tmSFVZcW9zQUlScDkKK3VySndSTStKSU5XR0c0Y1g3czBQbmF4V0hBb0RielFvbUN6QUJsRkROSVFyd1dDampURDRzRjNsRUZSa3FELwo0QUVkTmdPQWlJK3Q0dENkTXpidDZyS3E4Y1piQW1rWE5OTVRLMXBtMlQxRldzMkRuOEZheDdEbjg2UEd3c1J5CjdOVkZRTXZKeERYUFUyU0dzQTRXcEkvUVZOMzcxSUJrdDB4clpHQkM2aGJvR2pWenhGcXQvRUt1bjVJN0l6VzAKRkQva0ttbnR1VndYd1M2ZWI2V092VEJBbUFqR3NoV3VBZlRtc093QldUbVZsdEpnUlluUDVRODhHRWk4cDRkdgpIUmRISERWdWdPRDdMU21aZ0RjMVlVL2d2SFJONkxlUzRUSzRZTjVxTitWakgwbFp6RTJQL1JOUHFQdCtFLyt3CkFKa0hLd0lEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFBZEpPVnhiQ2VSMC9BUUlQN3dQRXM3U3o1QUJEc3ViMWVqRQpwMmpqY2gycnhyNDV0disxK3lvb01mSVVHTnFxbmJNOWJIVFBoNlpPWER0eHU5amRjWVhqV0J4eGdSd1Rkam96CmhGdXlyYmFhUEdvUThKTGZ3bjI5OENiblgraGUvSUVYOExUbFhERWZlbmdNdTNSTmJQekpPcE52NDd4c05aWlUKdDhRc2pLOUgrcFlhL0dQd3hzcTdVWDhNYUlYcnowVmVpS0dkK0laY1BGdVE3OE5FZmw0Q0l3SU9MOUxIcWxlSgo3eEl4emY3azNIRzBUaXF5aWU1VklFTlVTZTYzY2pOdlU5MTgwQkJCaFAwbVdnZlRFZFdnNW9iUXB6a3RieHplCmkwZ0RxTWJSVis5aVd1TnVBdDlwMUJyVDY0T0Jrb3doekh4dnJEOEZHT0lTL0lwVFZuaz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=")
////			.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcFFJQkFBS0NBUUVBcXdvS2ZIVVlxb3NBSVJwOSt1ckp3Uk0rSklOV0dHNGNYN3MwUG5heFdIQW9EYnpRCm9tQ3pBQmxGRE5JUXJ3V0NqalRENHNGM2xFRlJrcUQvNEFFZE5nT0FpSSt0NHRDZE16YnQ2cktxOGNaYkFta1gKTk5NVEsxcG0yVDFGV3MyRG44RmF4N0RuODZQR3dzUnk3TlZGUU12SnhEWFBVMlNHc0E0V3BJL1FWTjM3MUlCawp0MHhyWkdCQzZoYm9HalZ6eEZxdC9FS3VuNUk3SXpXMEZEL2tLbW50dVZ3WHdTNmViNldPdlRCQW1BakdzaFd1CkFmVG1zT3dCV1RtVmx0SmdSWW5QNVE4OEdFaThwNGR2SFJkSEhEVnVnT0Q3TFNtWmdEYzFZVS9ndkhSTjZMZVMKNFRLNFlONXFOK1ZqSDBsWnpFMlAvUk5QcVB0K0UvK3dBSmtIS3dJREFRQUJBb0lCQUhRNUJYa2V4SjBXaVFiMQpqb2VUUlhkS1VSN1VsQXRUdnpvWjVsMVBvQ0RkVFlwUU5HRXlYbEFCMjlialkyemF6S1UvRzZDWklGMkFxU0orCi9vQkRvWXJUaEdMSFp6eEp6NWcraWhyQ1A4QzhyWmtNdWpVdC9NZW56VjF1OGhMS3RkYzN0eTBjZS9FbDUranYKUHJ6S0N4NEZuRndVeUlYOU9EeW54aUVhakZFdHplVWpmUkdGcTMxSDhianJSVTArbTc0dGlPV3A2U1pIMjRvcgpDdmdxQ2I1TmtwR2Ircy8yaGlMNWFXbEtsSElaanIwM0RHWDFXNDRaVmRtTjhOM0lMbHZaZkdhbHpRTWcwQkc2Ci93QUlacUlVTGdKMEw0WlNlT2pPTzhKSEwyWnlncldtRFBsNUw0THdQM05OckJJTU1yMHhNSUN1MjRCV2tnbUIKSi9CUVRSa0NnWUVBeWhTK3FrWWljazMxTXVvL2xmMzJxR2wraEFIdlIyN3lIM3p3WHZkTXFSVkZlcytIRWtqbQpyb25TeUZQMVIxMTEza2srUkRXb1JCd1NMcHZ1OG43emhUenRmaU9CVEg3Vk0rSXl6V2VscmxERk83MjY3SkhTCkhCd0RiOFlrUktmdDkrRUN6aEpJWmlQMEJmOTFPOEV1MlNoc2N2YWplcWFVQzdyYkM5Q2w1Z1VDZ1lFQTJLejMKNGxlM2RqN0ZRRnpZeS81cExhOWVKOGxYOGd6OEFWc0I3SnB6ekN5R2tOaFVsRVVTTG85TitFeDBCSlFKMktBVwpFYU5uMVpHRWNMb3pOWmM5cEg4N2xrNkU1VHE5UUtLTHR6NGViY2FLcFpkejdVN3RKV2lIc29wLytMRDlPM29GCjZXT1ZVc1dZS3ZTK0ZhellkdVRsVkMxcjRiclVHb0VXc3Rad0QyOENnWUVBbEZMcnE0RTA4VjhsRlV6NGhQaFcKWGtDb1d1NHNtZXJtRHBTZXZhV1lnQmFiMjh1TklhK2VtZ2VSVGZiSjlxcXp4QWRRaTdJeW9tTlVPUis5SUFKcwpiYVJOMFNMd1daeFNrdlY1UW1qTUpBa202M1dVU09XektXeXFYUlU3TTZWcXRtRlN0THlZcFZmSVdjcXFXc1N0CnhUaTFlU0JzMTY2Q3hhVk45L3NhZVowQ2dZRUFyTXkxSEZQc1FtY3BGSDEzZ0tpeTVQWlkxczRpVHkyL3hLejUKeWVJYnU1Yys4a0Uwb3RLNi8xcldTZEZmTFUrSzBySkpKMlAwS1VUV0pMaTZvbUN0am9UUjFydGtEUlJkWGpERAowQzNBUXJmMlZyMkVmSnRYVVdBZDZHdkFSMnVQYVdHVldWNUw5aWtUaEI2UGdlWEk5SGp6MDhBdHEyWStSYXp4CmwwcWhNQTBDZ1lFQWsvMkxkWHhtczNRUm5EUTBzb2Y4M0FtNURkWEcyNWtmYnFsVngwV2dPRnFBeUIwbW1zRXcKTFlXMnVXTlI3NWk4MjJaT3ZoNGJXc2RCZDBuUkR1SXZUWnZGekNjcEZ3all2cEdaR0I1cmxjbjlDZ045Nk1nUApxRDgzbW5lTDYyOEE0OXJEQ3VDR2Z6VFJOdEo1aFc2ODlsbzFhWUJrOUJTR1JnYjhBK1Vzd1hFPQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=")
//			.withMasterUrl("http://39.106.124.113:8888")
//			.build();
	
    public static ExtendedKubernetesClient getClient() throws Exception {
        return new ExtendedKubernetesClient(config);
    }
    
    public static VirtualMachine getVMByName(String name) throws Exception {
        return getClient().virtualMachines().get(name);
    }

    public static VirtualMachineImage getVMImageByName(String name) throws Exception {
        return getClient().virtualMachineImages().get(name);
    }

    public static VirtualMachineDisk getVMDiskByName(String name) throws Exception {
        return getClient().virtualMachineDisks().get(name);
    }

    public static VirtualMachinePool getVMPoolByName(String name) throws Exception {
        return getClient().virtualMachinePools().get(name);
    }

    public static VirtualMachineDiskImage getVMDiskImageByName(String name) throws Exception {
        return getClient().virtualMachineDiskImages().get(name);
    }

    public static VirtualMachineNetwork getVMNetworkByName(String name) throws Exception {
        return getClient().virtualMachineNetworks().get(name);
    }
}

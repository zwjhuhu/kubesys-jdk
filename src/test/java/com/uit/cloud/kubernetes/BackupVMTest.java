package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle;

public class BackupVMTest {
    public static void main(String[] args) throws Exception {

        ExtendedKubernetesClient client = AbstractTest.getClient();
        boolean successful = client.virtualMachines()
                .backupVM("3955a6531be844979f922fe7a25fdee9", "vm.node114", getBackupVM());
        System.out.println(successful);
    }

    public static Lifecycle.BackupVM getBackupVM() {
        Lifecycle.BackupVM backupVM = new Lifecycle.BackupVM();
        backupVM.setPool("9277cbf968da49fbadbd91eff98f8a80");
        backupVM.setVersion("backup2");
        backupVM.setAll(true);
//        backupVM.setRemote("172.16.1.214");
//        backupVM.setPort("21");
//        backupVM.setUsername("ftpuser");
//        backupVM.setPassword("ftpuser");
        return backupVM;
    }
}

package com.uit.cloud.kubernetes;

import com.github.kubesys.kubernetes.ExtendedKubernetesClient;
import com.github.kubesys.kubernetes.api.model.virtualmachine.Lifecycle;

public class DeleteVMBackupTest {
    public static void main(String[] args) throws Exception {

        ExtendedKubernetesClient client = AbstractTest.getClient();
        boolean successful = client.virtualMachines()
                .deleteVMBackup("3955a6531be844979f922fe7a25fdee9", "vm.node114", getDeleteVMBackup());
        System.out.println(successful);
    }

    public static Lifecycle.DeleteVMBackup getDeleteVMBackup() {
        Lifecycle.DeleteVMBackup deleteVMBackup = new Lifecycle.DeleteVMBackup();
        deleteVMBackup.setPool("9277cbf968da49fbadbd91eff98f8a80");
        deleteVMBackup.setVersion("backup2");
        return deleteVMBackup;
    }
}

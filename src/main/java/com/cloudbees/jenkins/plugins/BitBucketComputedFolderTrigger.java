package com.cloudbees.jenkins.plugins;

import com.cloudbees.hudson.plugins.folder.computed.ComputedFolder;
import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.Item;
import hudson.triggers.Trigger;
import hudson.triggers.TriggerDescriptor;
import hudson.util.FormValidation;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Logger;

public class BitBucketComputedFolderTrigger extends Trigger<ComputedFolder> {

    private static final Logger LOGGER = Logger.getLogger(BitBucketComputedFolderTrigger.class.getName());

    private String overrideUrl;

    @DataBoundConstructor
    public BitBucketComputedFolderTrigger() { }

    // notice that the name of the getter must exactly like the parameter
    public String getOverrideUrl() {
        return overrideUrl;
    }

    @DataBoundSetter
    public void setOverrideUrl(String overrideUrl){
        this.overrideUrl = overrideUrl;
    }

    @Extension
    public static class ComputedFolderDescriptor extends TriggerDescriptor {

        // Must be inside the DescriptorImpl
        @SuppressWarnings("unused")
        public FormValidation doCheckOverrideUrl(@QueryParameter String value) throws IOException, ServletException {
            return FormValidation.ok();
        }

        @Override
        public boolean isApplicable(Item item) {
            LOGGER.warning(item.getClass().getSimpleName());
            return item instanceof ComputedFolder;
        }

        @NonNull
        @Override
        public String getDisplayName() {
            return "Build when a change is pushed to BitBucket";
        }
    }
}

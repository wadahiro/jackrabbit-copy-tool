package org.apache.jackrabbit.tools.copy;

import org.apache.jackrabbit.core.RepositoryCopier;
import org.apache.jackrabbit.core.config.RepositoryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The JcrCopy tool for copy of repository.
 * 
 * <h1>Copy</h1> Options (all required):
 * <ul>
 * <li>-sc filename : specifies the filename of the source repository
 * configuration (repository.xml)</li>
 * <li>-sd directory : specifies the target repository home directory</li>
 * <li>-tc filename : specifies the filename of the source repository
 * configuration (repository.xml)</li>
 * <li>-td directory : specifies the target repository home directory</li>
 * </ul>
 * 
 * @author Hiroyuki Wada
 * 
 */
public class JcrCopyTool {

    private static Logger logger = LoggerFactory.getLogger(JcrCopyTool.class);

    public static void main(String[] args) {

        String sourceRepoConfig = null;
        String sourceRepoDir = null;
        String targetRepoConfig = null;
        String targetRepodir = null;

        // try to parse the argument list:
        for (int i = 0; i < args.length; i++) {
            if ("-sc".equals(args[i]) && i + 1 < args.length) {
                sourceRepoConfig = args[i + 1];
                i++;
            } else if ("-sd".equals(args[i]) && i + 1 < args.length) {
                sourceRepoDir = args[i + 1];
                i++;
            } else if ("-tc".equals(args[i]) && i + 1 < args.length) {
                targetRepoConfig = args[i + 1];
                i++;
            } else if ("-td".equals(args[i]) && i + 1 < args.length) {
                targetRepodir = args[i + 1];
                i++;
            }
        }

        if (sourceRepoConfig == null || sourceRepoDir == null
                || targetRepoConfig == null || targetRepodir == null) {
            logger.error("Invalid arguments.\nUsage:\n"
                    + "  jackrabbit-copy-tool -sc <path to source repository.xml> \\\n"
                    + "\t\t-sd <path to source repository home directory> \\\n"
                    + "\t\t-tc <path to target repository.xml> \\\n"
                    + "\t\t-td <path to target repository home directory>");
            return;
        }

        // Execute copy!
        new JcrCopyTool().doCopy(sourceRepoConfig, sourceRepoDir,
                targetRepoConfig, targetRepodir);
    }

    private void doCopy(String sourceRepoConfig, String sourceRepoDir,
            String targetRepoConfig, String targetRepoDir) {

        try {
            RepositoryConfig fromRepoConfig = RepositoryConfig.create(
                    sourceRepoConfig, sourceRepoDir);
            RepositoryConfig toRepoConfig = RepositoryConfig.create(
                    targetRepoConfig, targetRepoDir);

            RepositoryCopier.copy(fromRepoConfig, toRepoConfig);

        } catch (Exception e) {
            logger.error("copy failed: rollback", e);
        }
    }
}

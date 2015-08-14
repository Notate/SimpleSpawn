# SimpleSpawn
- The easiest way to customize the most easy plugin.

Only thing you need to change is config.yml in `server-folder/plugins/SimpleSpawn/config.yml`

# Config.yml
```
config:
  # If permtospawn is true, you need the permission to execute the command /spawn. (Default: simplespawn.spawn)
  permtospawn: false

# Supporting chatcolors like &0-1, &a-f
messages:
  nopermission: "&cYou don't have permission to perform this command"
  spawn: "&aWoosh! Teleported to spawn!"
  setspawn: "&aSpawn set"
  onlyplayers: "&cOnly players can execute this command"

# Customize permissions to each command
permissions:
  setspawn: simplespawn.setspawn
  spawn: simplespawn.spawn
```

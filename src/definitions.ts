export interface DeezerSDKPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

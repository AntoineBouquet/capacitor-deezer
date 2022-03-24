export interface DeezerSDKPlugin {
  initialize(options: {appId: string}): Promise<{ result: boolean }>;
  login(options: {permissions: string[]}): Promise<{ result: any }>;
  logout(): Promise<{ result: any }>;
  playTrack(options: {trackId: string}): Promise<void>;
  play(): Promise<{ result: boolean }>;
  pause(): Promise<{ result: boolean }>;
}

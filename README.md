# capacitor-deezer

Plugin to use Deezer into Ionic Capacitor projects

## Install

```bash
npm install capacitor-deezer
npx cap sync
```

## API

<docgen-index>

* [`initialize(...)`](#initialize)
* [`login(...)`](#login)
* [`logout()`](#logout)
* [`playTrack(...)`](#playtrack)
* [`play()`](#play)
* [`pause()`](#pause)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### initialize(...)

```typescript
initialize(options: { appId: string; }) => Promise<{ result: boolean; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ appId: string; }</code> |

**Returns:** <code>Promise&lt;{ result: boolean; }&gt;</code>

--------------------


### login(...)

```typescript
login(options: { permissions: string[]; }) => Promise<{ result: any; }>
```

| Param         | Type                                    |
| ------------- | --------------------------------------- |
| **`options`** | <code>{ permissions: string[]; }</code> |

**Returns:** <code>Promise&lt;{ result: any; }&gt;</code>

--------------------


<<<<<<< HEAD
### logout()

```typescript
logout() => Promise<{ result: any; }>
```

**Returns:** <code>Promise&lt;{ result: any; }&gt;</code>

--------------------


=======
>>>>>>> 83b4297253769ed608427dd419c08e22a22da4ec
### playTrack(...)

```typescript
playTrack(options: { trackId: string; }) => Promise<void>
```

| Param         | Type                              |
| ------------- | --------------------------------- |
| **`options`** | <code>{ trackId: string; }</code> |

--------------------

<<<<<<< HEAD

### play()

```typescript
play() => Promise<{ result: boolean; }>
```

**Returns:** <code>Promise&lt;{ result: boolean; }&gt;</code>

--------------------


### pause()

```typescript
pause() => Promise<{ result: boolean; }>
```

**Returns:** <code>Promise&lt;{ result: boolean; }&gt;</code>

--------------------

=======
>>>>>>> 83b4297253769ed608427dd419c08e22a22da4ec
</docgen-api>
